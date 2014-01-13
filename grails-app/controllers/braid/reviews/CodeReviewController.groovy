package braid.reviews

import grails.plugins.springsecurity.Secured
import braid.presenters.reviews.CodeReviewHomeworkListPresenter
import braid.presenters.reviews.CodeReviewResultsPresenter

class CodeReviewController {

	def dateService

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

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def list() {
		def currentCourse = courseService.currentCourse
		def currentTime = dateService.currentTime

		def presenter = new CodeReviewHomeworkListPresenter(
			currentCourse: courseService.currentCourse,
			currentUser: userService.currentUser,
			currentTime: dateService.currentTime)

		model: [presenter: presenter]
	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def show() {
		def homework = CodeReviewHomework.get(params.id)
		redirect action: homework.stage, id: params.id
	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN'])
	def solve(CodeReviewSolutionCommand command) {

		def user = userService.currentUser
		def homework = CodeReviewHomework.get(params.long('id'))

		if (homework.stage != 'solve') {
			flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.notInStage')
			redirect action: 'list'
			return
		}

		if (request.method == 'GET') {

			def solution = CodeReviewSolution.findByUserAndHomework(user, homework)
			[homework: homework, previousSolution: solution]

		} else {

			if (command.validate()) {

				solveCodeReviewService.buildSolution(command, homework, user)

				flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.solved')
				redirect action: 'list'
			} else {
				render view: 'solve', model: [homework: homework, previousSolution: command]
			}

		}
	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN'])
	def review(CodeReviewCommand command) {

		def user = userService.currentUser
		def homework = CodeReviewHomework.get(params.long('id'))

		if (homework.stage != 'review') {
			flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.notInStage')
			redirect action: 'list'
			return
		}

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

				flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.reviewed')
				redirect action: 'list'
			} else {
				render view: 'review', model: [homework: homework, solution: solution, review: command]
			}
		}
	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN'])
	def results() {
		def user = userService.currentUser
		def homework = CodeReviewHomework.get(params.long('id'))

		if (homework.stage != 'results') {
			flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.notInStage')
			redirect action: 'list'
			return
		}

		def solution = codeReviewResultsService.getOwnSolution(homework, user)
		def reviews = codeReviewResultsService.findAllReviews(solution)

		if (!homework || !solution || !reviews) {
			flash.message = g.message(code: 'braid.reviews.CodeReview.stages.review.noResults')
			redirect action: 'list'
		}

		def presenter = new CodeReviewResultsPresenter(homework: homework, solution: solution,
			reviews: reviews)
		[results: presenter]
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
