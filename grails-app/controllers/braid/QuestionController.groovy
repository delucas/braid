package braid


class QuestionController {

	def courseService

	private getNextQuestionNumber(){
		Question.executeQuery('select max(q.id) from Question q')[0] + 1 
	}
	
	def create() {
		model: [nextQuestionNumber: nextQuestionNumber]
	}
	
	def save(QuestionCommand command) {
		if (command.validate()) {
			Course theCourse = courseService.currentCourse
			
			def question = new Question()
			question.wording = command.wording
			question.level = command.level
			question.tags = command.tags.split(',').collect { it -> it.trim() }
			question.course = theCourse
			question.save(flush:true)
			
			flash.message = 'Se ha creado correctamente la pregunta'
			redirect(action:'list')
		} else {
			render view:'create', model: [command: command, nextQuestionNumber: nextQuestionNumber]
		}
	}
		
    def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		
		def course = courseService.currentCourse
		def questions = Question.findAllByCourse(course, [max:params.max, offset:params.offset])
		def questionsTotal = Question.countByCourse(course)
		
		model: [questions: questions, questionsTotal: questionsTotal]
	}
	
	def random() {
		def course = courseService.currentCourse
		def questions = Question.executeQuery('from Question q where q.course = :course order by rand()', [course: course, max: 1])
		render view: 'list', model: [questions: questions]
	}
	
	def exam(Integer size) {
		def course = courseService.currentCourse
		// TODO: algoritmo que nos brinde una lista equilibrada de puntos.
		// Por ahora, es un random con límite 5
		def questions = Question.executeQuery('from Question q where q.course = :course order by rand()', [course: course, max: (size?size:5)])
		render view: 'list', model: [questions: questions]
	}
}
