package braid.reviews

import liquibase.statement.core.CommentStatement;
import braid.UserService;
import grails.validation.Validateable

class CodeReviewController {

	def userService
	def courseService

	def solveCodeReviewService
	def reviewSolutionService
	def codeReviewResultsService

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
		// TODO: filter by dates

		if (request.method == 'GET') {

			def solution = CodeReviewSolution.findByUserAndHomework(user, homework)
			[homework: homework, previousSolution: solution]

		} else {

			if (command.validate()) {

				solveCodeReviewService.buildSolution(command, homework, user)

				redirect action:'list'
			} else {
				render view: 'solve', model: [homework: homework, previousSolution: command]
			}

		}
	}

	def review(CodeReviewCommand command) {

		def user = userService.currentUser
		def homework = CodeReviewHomework.get(params.long('id'))
		// TODO: filter by dates

		if (request.method == 'GET') {
			def solution = reviewSolutionService.fetch(homework, user)

			if (!solution) {
				flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.done')
				redirect action: 'list'
			}
			// TODO: use a presenter to accomodate view
			[homework: homework, solution: solution, currentUser: user]
		} else {
			def solution = CodeReviewSolution.get(command.solutionId)
			if (command.validate()) {

				reviewSolutionService.buildReview(command, solution, user)

				redirect action: 'list'
			} else {
				render view: 'review', model: [homework: homework, solution: solution, review: command]
			}
		}
	}

	def results() {
		def user = userService.currentUser
		def homework = CodeReviewHomework.get(params.long('id'))
		// TODO: filter by dates
		def solution = codeReviewResultsService.getOwnSolution(homework, user)
		def reviews = codeReviewResultsService.findAllReviews(solution)

		if (!homework || !solution || !reviews) redirect action: 'list'

		// TODO: enhance with a presenter, so that my own review is in last place
		[homework: homework, solution: solution, reviews: reviews]
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

class CodeReviewCommand {
	Long solutionId
	Boolean honorCode

	Integer clarity
	Integer conventions
	Integer correctness
	Integer tests

	String comments
	String best
	String advice

	static constraints = {
		solutionId validator: { val ->
			CodeReviewSolution.get(val) != null
		}
		honorCode validator: { val -> val==true }

		clarity range: 0..3
		conventions range: 0..3
		correctness range: 0..3
		tests range: 0..3

		comments blank: false
		best blank: false
		advice blank: false
	}

	static mapping = {
		comments type: 'text'
		best type: 'text'
		advice type: 'text'
	}

	CodeReview toEntity() {
		def review = new CodeReview()

		review.clarity = clarity
		review.conventions = conventions
		review.correctness = correctness
		review.tests = tests

		review.comments = comments
		review.best = best
		review.advice = advice

		review
	}

}
