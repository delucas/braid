package braid

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.validation.Validateable
import braid.github.Repository
import braid.presenters.AssignmentPresenter
import braid.presenters.assignment.AssignmentListPresenter

class AssignmentController {

	def dateService

	def userService
	def courseService

	def graderService

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
    def list() {

		def presenter = new AssignmentListPresenter(
			currentCourse: courseService.currentCourse,
			currentUser: userService.currentUser,
			currentTime: dateService.currentTime)

		model: [presenter: presenter]
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
		def user = courseService.currentCourse.settings.githubUsername
		new Repository(user: user, name: repoName)
	}

	private Date convertToUTC(Date dateInZone, TimeZone tz) {
		// TODO: es esto REALMENTE necesario?
		def cal = dateInZone.toCalendar()
		cal.timeZone = tz
		cal.time
	}

	// TODO: please refactor
	private convertData(originalData, title) {

		def result = []

		def student
		def data = []

		originalData.each {
			if (it[0] == title) {
				if (!student || student == it[1]) {
					data << [(int)(it[2].time/10), it[3]]
					student = it[1]
				} else {
					result << ['name': student, 'data': data]
					student = it[1]
					data = []
				}
			}
		}
		if(student) result << ['name': student, 'data': data]

		return result

	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI'])
	def statistics(Long id) {

		def assignment = Assignment.get(id)

		// TODO: please refactor
		def assignmentSolutions = AssignmentSolution.executeQuery('select s.assignment.title as title, s.user.name, s.dateCreated, s.score ' +
			'from AssignmentSolution s where s.score > 0 order by s.assignment.title, s.user.name, s.dateCreated asc')
		def graph = convertData(assignmentSolutions, assignment.title)

		render view: 'statistics', model: [assignment:assignment, assignmentGraph: graph as JSON]

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

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN'])
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

