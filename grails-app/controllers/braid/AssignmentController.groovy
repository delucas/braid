package braid

import grails.plugins.springsecurity.Secured
import grails.validation.Validateable
import groovy.json.JsonSlurper
import braid.github.Repository
import braid.presenters.AssignmentPresenter

class AssignmentController {

	def dateService
	
	def userService
	def courseService
	
	def graderService

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
    def list() {
		
		def currentCourse = courseService.currentCourse
		def assignments = Assignment.findAllByCourse(currentCourse,[sort: 'dueDate'])
		
		model: [assignments: assignments]
	}
	
	@Secured(['ROLE_JEDI'])
	def create() {
		
	}
	
	@Secured(['ROLE_JEDI'])
	def save(AssignmentCommand command) {
		
		if (command.validate()) {
	
			def assignment = new Assignment()
			assignment.title = command.title
			assignment.repo = createRepository(command.repoName)
			assignment.dueDate = convertToUTC(command.dueDate, userService.currentTimeZone)
			assignment.course = courseService.currentCourse
			
			assignment.save(flush: true)
			
			flash.message = 'Se ha creado correctamente el trabajo práctico'
			redirect(action:'list')
			
		} else {
			render view:'create', model: [command: command]
		}
		
	}
	
	private def createRepository(String repoName) {
		new Repository(user: 'tallerweb', name: repoName)
	}
	
	private Date convertToUTC(Date dateInZone, TimeZone tz) {
		// TODO: es esto REALMENTE necesario?
		def cal = dateInZone.toCalendar()
		cal.timeZone = tz
		cal.time
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
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
			solutions: solutions,
			padawan: userService.currentUser.hasRole('ROLE_PADAWAN')
			)
	}
	
	@Secured(['ROLE_PADAWAN'])
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
				
				graderService.send(solution)
				
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
	
	@Secured(['ROLE_JEDI'])
	def feedback() {
		def solution = AssignmentSolution.get(params.id)
		[solution: solution]
	}
	
}

@Validateable
class AssignmentCommand {

//	def dateService
	
	String title
	String repoName
	Date dueDate
		
	static constraints = {
		title blank: false
		repoName blank: false
		dueDate validator: { val ->
//			val.after(dateService.currentTime)
			val.after(new Date())
		}
	}
}

