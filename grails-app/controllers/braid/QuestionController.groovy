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
		def questions = Question.findAllByCourse(course, [max:params.max, offset:params.offset])
		def questionsTotal = Question.countByCourse(course)
		
		model: [questions: questions, questionsTotal: questionsTotal]
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def random() {
		def course = courseService.currentCourse
		def questions = Question.executeQuery('from Question q where q.course = :course order by rand()', [course: course, max: 1])
		render view: 'list', model: [questions: questions]
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def exam(Integer size) {
		def course = courseService.currentCourse
		// TODO: algoritmo que nos brinde una lista equilibrada de puntos.
		// Por ahora, es un random con límite 5
		def questions = Question.executeQuery('from Question q where q.course = :course order by rand()', [course: course, max: (size?size:5)])
		render view: 'list', model: [questions: questions]
	}
}
