package braid

import grails.plugins.springsecurity.Secured


class QuestionController {

	def courseService

	private getNextQuestionNumber(){
		def list = Question.executeQuery('select max(q.position) from Question q')
		list ? list[0] + 1 : 1 
	}

	@Secured(['ROLE_JEDI'])
	def create() {
		model: [nextQuestionNumber: nextQuestionNumber]
	}
	
	@Secured(['ROLE_JEDI'])
	def save(QuestionCommand command) {
		if (command.validate()) {
			Course theCourse = courseService.currentCourse
			
			def question = new Question()
			question.wording = command.wording
			question.level = command.level
			question.position = nextQuestionNumber
			question.tags = command.tags.split(',').collect { it -> it.trim() }
			question.course = theCourse
			question.save(flush:true)
			
			flash.message = 'Se ha creado correctamente la pregunta'
			redirect(action:'list')
		} else {
			render view:'create', model: [command: command, nextQuestionNumber: nextQuestionNumber]
		}
	}
		
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
    def list() {
		params.max = Math.min(params.max ? params.int('max') : 5, 10)
		
		def course = courseService.currentCourse
		def questions = Question.findAllByCourse(course, [max:params.max, offset:params.offset, sort: 'position'])
		def questionsTotal = Question.countByCourse(course)
		
		model: [questions: questions, questionsTotal: questionsTotal]
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def random() {
		def course = courseService.currentCourse
		def questionIds = Question.executeQuery('select distinct q.id from Question q where q.course = :course', [course: course])
		def questions = Question.get(questionIds[new Random().nextInt(questionIds.size())]) as List
		render view: 'list', model: [questions: questions]
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def exam() {
		def course = courseService.currentCourse
		// TODO: algoritmo que nos brinde una lista equilibrada de puntos.
		// Por ahora, es un random con límite 5
		
		def questionIds = Question.executeQuery('select distinct q.id from Question q where q.course = :course', [course: course])
		Collections.shuffle(questionIds)
		def max = (questionIds.size() > 4) ? 5 : questionIds.size() 
		
		def questions = Question.findAllByIdInList(questionIds[0..(max-1)], [sort: 'position'])
		render view: 'list', model: [questions: questions]
	}
}
