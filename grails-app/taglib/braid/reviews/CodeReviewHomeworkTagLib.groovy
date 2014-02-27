package braid.reviews

import braid.presenters.reviews.CodeReviewHomeworkPresenter

class CodeReviewHomeworkTagLib {

	def dateService
	def userService

	static namespace = 'codeReview'

	def detailsLink = { attrs ->
		def homework = attrs.homework
		def currentUser = userService.currentUser

		CodeReviewHomeworkPresenter presenter = new CodeReviewHomeworkPresenter(
			now: dateService.currentTime, homework: homework)
		def stage = presenter.stage
		if (currentUser.hasRole('ROLE_JAR_JAR')) {
			stage = 'show'
		}

		out << g.link(controller: 'codeReview', action: stage, id: homework.id,'class': 'btn') {
			g.message(code: "braid.reviews.CodeReviewHomework.stages.$stage")
		}
	}
}