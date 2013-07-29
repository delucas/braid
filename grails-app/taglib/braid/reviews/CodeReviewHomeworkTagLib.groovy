package braid.reviews

import braid.presenters.reviews.CodeReviewHomeworkPresenter

class CodeReviewHomeworkTagLib {

	def dateService

	static namespace = 'codeReview'

	def detailsLink = { attrs ->
		def homework = attrs.homework
		CodeReviewHomeworkPresenter presenter = new CodeReviewHomeworkPresenter(
			now: dateService.currentTime, homework: homework)
		def stage = presenter.stage
		out << g.link(controller: 'codeReview', action: stage, id: homework.id,'class': 'btn') {
			g.message(code: "braid.reviews.CodeReviewHomework.stages.$stage")
		}
	}
}