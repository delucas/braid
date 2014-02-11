package braid.reviews

import braid.User

class CodeReview {

	Date dateCreated

	CodeReviewSolution solution
	User user

	Integer clarity
	Integer conventions
	Integer correctness
	Integer tests

	String comments
	String best
	String advice

}
