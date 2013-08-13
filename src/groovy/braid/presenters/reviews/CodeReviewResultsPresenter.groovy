package braid.presenters.reviews

import braid.reviews.CodeReview
import braid.reviews.CodeReviewHomework
import braid.reviews.CodeReviewSolution


class CodeReviewResultsPresenter {

	CodeReviewHomework homework
	CodeReviewSolution solution
	List<CodeReview> reviews

	List<CodeReview> getReviews() {
		reviews - ownReview
	}
	
	CodeReview getOwnReview() {
		reviews.find { it -> it.user == solution.user }
	}
}