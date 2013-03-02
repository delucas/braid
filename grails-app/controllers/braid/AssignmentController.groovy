package braid

import braid.presenters.AssignmentPresenter

class AssignmentController {

	def userService
	def courseService
	def dateService

    def list() {
		
		def currentCourse = courseService.currentCourse
		def assignments = Assignment.findAllByCourse(currentCourse,[sort: 'dueDate'])
		
		model: [assignments: assignments]
	}
	
	def show(Long id) {
		
		def assignment = Assignment.get(id)
		
		def presenter = buildPresenter(assignment)
		
		render view: 'show', model: [presenter: presenter]
		
	}

	private def buildPresenter(def assignment) {
		
		def solutions = AssignmentSolution.findAllByUserAndAssignment(
			userService.currentUser,
			assignment,
			[sort: 'dateCreated', order: 'desc'])
		
		new AssignmentPresenter(
			assignment: assignment,
			course: courseService.currentCourse,
			now: dateService.currentTime,
			solutions: solutions
			)
	}
	
	def solve(Long assignmentId, AssignmentSolutionCommand command) {
		
		def assignment = Assignment.get(assignmentId)
		
		if (!assignment.outOfDate) {
			
			if (command.validate()) {
				def user = userService.currentUser
				
				def solution = new AssignmentSolution()
				solution.assignment = assignment
				solution.user = user
				solution.dateCreated = dateService.currentTime
				
				solution.save(flush: true)
				
				// TODO: enviar a graduación!
				
				flash.message = 'Tu respuesta se ha guardado correctamente. Recordá que podés reentregar tantas veces como quieras dentro del período de vigencia.'
				redirect action: 'show', params: [id: assignmentId]
				
			} else {
				def presenter = buildPresenter(assignment)
				presenter.command = command
				render view:'show', model: [presenter: presenter]
			}
		} else {
			flash.message = 'No se puede responder fuera de fecha'
			redirect action: 'show', params: [id: assignmentId]
		}
	}
	
	def feedback() {
		def solution = AssignmentSolution.get(params.id)
		[solution: solution]
	}
	
}
