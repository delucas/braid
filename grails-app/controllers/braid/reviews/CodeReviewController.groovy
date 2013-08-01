package braid.reviews

import braid.UserService;
import grails.validation.Validateable

class CodeReviewController {

	def userService
	def courseService

	def solveCodeReviewService

	static allowedMethods = [
		list: 'GET',
		solve: ['GET', 'POST'],
		pair: ['GET', 'POST'],
		self: ['GET', 'POST'],
		results: 'GET'
	]

	def list() {
		[homeworkList:
			CodeReviewHomework.findAllByCourse(courseService.currentCourse,
			[sort: 'solutionDueDate', order: 'desc'])]
	}

	def solve(CodeReviewSolutionCommand command) {

		def user = userService.currentUser
		def homework = CodeReviewHomework.get(params.long('id'))

		if (request.method == 'GET') {

			def solution = CodeReviewSolution.findByUserAndHomework(user, homework)
			[codeReview: homework, previousSolution: solution]

		} else {

			if (command.validate()) {

				def solution = solveCodeReviewService.buildSolution(command, homework, user)
				solution.save()

				redirect action:'list'
			} else {
				render view:'solve', model: [codeReview: homework, previousSolution: command]
			}

		}
	}

	def review() {
		if (request.method == 'GET') {
			//TODO: reuse pair view, with own solution
			println params.id
		} else {
			flash.message = 'TBD'
			redirect action:'list'
		}
	}

	def results() {
	}
}

class CodeReviewSolutionCommand {
	Long solutionId
	Boolean honorCode
	Integer gist

	static constraints = {
		honorCode validator: { val -> val==true }
		gist blank: false
		solutionId nullable: true, validator: { val ->
			val == null || CodeReviewSolution.get(val) != null
		}
	}

	CodeReviewSolution toEntity() {
		def solution = this.solutionId ? CodeReviewSolution.get(solutionId) :  new CodeReviewSolution()
		solution.gist = this.gist
		solution
	}

	Long getId() {
		solutionId?:null
	}
}
