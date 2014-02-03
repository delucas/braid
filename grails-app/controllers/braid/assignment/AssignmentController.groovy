package braid.assignment

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.validation.Validateable
import braid.AssignmentSolutionCommand
import braid.github.Repository
import braid.presenters.assignment.AssignmentListPresenter
import braid.presenters.assignment.AssignmentPresenter

class AssignmentController {

	def dateService

	def userService
	def courseService

	def graderService

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
    def list() {

		def presenter = new AssignmentListPresenter(
			currentCourse: courseService.currentCourse,
			currentUser: userService.currentUser,
			currentTime: dateService.currentTime)

		model: [presenter: presenter]
	}

	@Secured(['ROLE_JEDI'])
	def create() {
		[currentYear: dateService.currentTime.getAt(Calendar.YEAR),
			currentTime: dateService.currentTimeInZone]
	}

	@Secured(['ROLE_JEDI'])
	def save(AssignmentCommand command) {

		if (command.validate()) {

			def assignment = new Assignment().with {
				title = command.title
				repo = createRepository(command.repoName)
				startDate = dateService.toUTC(command.startDate, userService.currentTimeZone)
				dueDate = dateService.toUTC(command.dueDate, userService.currentTimeZone)
				course = courseService.currentCourse

				return it
			}

			assignment.save(flush: true)

			flash.message = g.message(code: 'braid.assignment.Assignment.create.success')
			redirect(action:'list')

		} else {
			render view:'create', model: [
				command: command, currentYear: dateService.currentTime.getAt(Calendar.YEAR),
				currentTime: dateService.currentTimeInZone
			]
		}
	}

	private createRepository(String repoName) {
		def user = courseService.currentCourse.settings.githubUsername
		new Repository(user: user, name: repoName)
	}

	// TODO: please refactor
	private convertData(originalData, title) {

		def result = []

		def student
		def data = []

		originalData.each {
			if (it[0] == title) {
				if (!student || student == it[1]) {
					data << [(int)(it[2].time / 10), it[3]]
					student = it[1]
				} else {
					result << ['name': student, 'data': data]
					student = it[1]
					data = []
				}
			}
		}
		if (student) {
			result << ['name': student, 'data': data]
		}

		result
	}

	@Secured(['ROLE_JEDI'])
	def statistics(Long id) {

		def assignment = Assignment.get(id)

		// TODO: please refactor
		def assignmentSolutions = AssignmentSolution.executeQuery(
			'select s.assignment.title as title, s.user.name, s.dateCreated, s.score ' +
			'from AssignmentSolution s where s.score > 0 order by s.assignment.title, s.user.name, s.dateCreated asc')
		def graph = convertData(assignmentSolutions, assignment.title)

		render view: 'statistics', model: [assignment:assignment, assignmentGraph: graph as JSON]

	}

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def show(Long id) {

		def assignment = Assignment.get(id)

		def presenter = buildPresenter(assignment)

		render view: 'show', model: [presenter: presenter]

	}

	private buildPresenter(def assignment) {

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

				flash.message = g.message(code: 'braid.assignment.Assignment.solve.success')
				redirect action: 'show', params: [id: assignmentId]

			} else {
				def presenter = buildPresenter(assignment)
				presenter.command = command
				render view:'show', model: [presenter: presenter]
			}
		} else {
			flash.message = g.message(code: 'braid.assignment.Assignment.solve.outOfDate')
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
	Date startDate
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
