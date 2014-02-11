package braid.reviews

import braid.User

class CodeReviewSolution {

	Integer gist
	Date dateCreated

	CodeReviewHomework homework
	User user

//	static hasMany = [reviews: CodeReview]

	Integer getRevisionsTotal() {
		CodeReview.countBySolution(this)
	}
}
