package braid


class HomeworkController {

	def userService
	def courseService

    def list() {
		
		def currentCourse = courseService.currentCourse
		def homeworks = Homework.findAllByCourse(currentCourse,[sort: "dueDate"])
		
		model: [homeworks: homeworks]
	}
	
	def show(Long id) {
		def user = userService.currentUser
		def homework = Homework.get(id)
		
		if (user.hasRole('JEDI')) {
			showIfJedi(homework)
		} 
		if (user.hasRole('PADAWAN')) {
			showIfPadawan(homework)
		}
		
	}
	
	private showIfPadawan(Homework homework) {
		def user = userService.currentUser
		def currentCourse = courseService.currentCourse
		
		def previousSolution = HomeworkSolution.findByUserAndHomework(user, homework)
		def command = HomeworkSolutionCommand.createFrom previousSolution
		
		render view: 'showIfPadawan', model: [homework: homework, course: currentCourse, command: command]
	}
	
	private showIfJedi(Homework homework) {
		render view: 'showIfJedi', model: [homework: homework]
	}

	def solve(Long homeworkId, HomeworkSolutionCommand command) {
		
		def homework = Homework.get(homeworkId)
		def course = courseService.currentCourse
		
		if (!homework.outOfDate) {
			
			if (command.validate()) {
				def user = userService.currentUser
				
				def solution = command.toHomeworkSolution()
				solution.homework = homework
				solution.user = user
				
				solution.save(flush: true)
				
				flash.message = 'Tu respuesta se ha guardado correctamente. Recordá que podés corregirla dentro del período de vigencia'
				redirect action: 'show', params: [id: homeworkId]
				
				
			} else {
				render view:'show', model: [homework: homework, course: course, command: command]
			}
		} else {
			// TODO: prohibido! No se puede responder fuera de fecha
		}
	}
	
}
