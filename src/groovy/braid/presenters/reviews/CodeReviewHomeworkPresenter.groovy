package braid.presenters.reviews

import braid.reviews.CodeReviewHomework


class CodeReviewHomeworkPresenter {

	CodeReviewHomework homework
	Date now

	def getStage() {
		if (homework.solutionDueDate?.after(now)) 'solve'
		else if (homework.reviewDueDate?.before(now)) 'results'
		else 'review'
	}
}