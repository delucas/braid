package braid

import braid.presenters.JediHomeworkPresenter
import braid.presenters.PadawanHomeworkPresenter


class HomeworkController {

	def userService
	def courseService
	def homeworkService
	def dateService

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
		
		def currentUser = userService.currentUser
		def homeworkSolution = HomeworkSolution.findByUserAndHomework(currentUser, homework)
		
		def presenter = new PadawanHomeworkPresenter(
				homework: homework,
				homeworkSolution: homeworkSolution,
				alreadySolved: homeworkSolution != null,
				course: courseService.currentCourse,
				now: dateService.currentTime
			)
		
		render view: 'showIfPadawan', model: [presenter: presenter]
	}
	
	private showIfJedi(Homework homework) {
		
		def presenter = new JediHomeworkPresenter(
				totalToGrade: homeworkService.countByHomeworkAndNotFeedback(homework),
				homework: homework,
				solutionsUpToDate: homeworkService.findAllByHomework(homework),
				now: dateService.currentTime
			)
		
		render view: 'showIfJedi', model: [presenter: presenter]
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
				solution.dateCreated = dateService.currentTime
				
				solution.save(flush: true)
				
				flash.message = 'Tu respuesta se ha guardado correctamente. Recordá que podés corregirla dentro del período de vigencia'
				redirect action: 'show', params: [id: homeworkId]
				
			} else {
				def presenter = new PadawanHomeworkPresenter(
						homework: homework,
						homeworkSolution: command,
						alreadySolved: false,
						course: course,
						now: dateService.currentTime
					)
				render view:'showIfPadawan', model: [presenter: presenter]
			}
		} else {
			flash.message = 'No se puede responder fuera de fecha'
			redirect action: 'show', params: [id: homeworkId]
		}
	}
	
	def grade(Long homeworkId) {
		def homework = Homework.get(homeworkId)

		def homeworkSolution = HomeworkSolution.find(
			'from HomeworkSolution s where s.homework = :homework and s.feedback is null order by rand()',
			[homework: homework])
				
		if (! homeworkSolution) {
			redirect action: 'show', params: [id: homeworkId]
		}
		
		[homework: homework, homeworkSolution: homeworkSolution]
		
	}
	
	def gradeDo(Long homeworkSolutionId, String feedback) {
		
		def homeworkSolution = HomeworkSolution.get(homeworkSolutionId)
		homeworkSolution.feedback = feedback
		homeworkSolution.save(flush: true)
		
		redirect action:'grade', params: [homeworkId: homeworkSolution.homework.id]
	}
	
}
