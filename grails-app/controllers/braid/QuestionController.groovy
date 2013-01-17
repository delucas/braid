package braid


class QuestionController {

	def courseService
	
	def random() {
		def course = courseService.currentCourse
		def questions = Question.executeQuery('from Question q where q.course = :course order by rand()', [course: course, max: 1])
		render view: 'list', model: [questions: questions]
	}
	
    def list() {
		def course = courseService.currentCourse
		def questions = Question.findAllByCourse(course)
		model: [questions: questions]
	}
	
	def exam() {
		def course = courseService.currentCourse
		// TODO: algoritmo que nos brinde una lista equilibrada de puntos.
		// Por ahora, es un random con límite 5
		def questions = Question.executeQuery('from Question q where q.course = :course order by rand()', [course: course, max: 5])
		render view: 'list', model: [questions: questions]
	}
}
