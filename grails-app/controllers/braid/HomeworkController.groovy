package braid


class HomeworkController {

	def userService
	def courseService

    def list() {
		
		def currentCourse = courseService.currentCourse
		def homeworks = Homework.findAllByCourse(currentCourse,[sort: 'dueDate'])
		
		model: [homeworks: homeworks]
	}
	
	def show(Long id) {
		
		def user = userService.currentUser
		def homework = Homework.get(id)
		
		// TODO: cuando se tenga la entidad UserCourse con 
		// role podemos pedir user.getRoleInCourse(course)
		// y mejorar esto por una llamada dinámica.
		
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
		def alreadySolved = previousSolution != null
		def command = HomeworkSolutionCommand.createFrom previousSolution
		
		render view: 'showIfPadawan', model: [homework: homework, course: currentCourse, command: command, alreadySolved: alreadySolved]
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
				render view:'showIfPadawan', model: [homework: homework, course: course, command: command]
			}
		} else {
			flash.message = 'No se puede responder fuera de fecha'
			redirect action: 'show', params: [id: homeworkId]
		}
	}
	
}
