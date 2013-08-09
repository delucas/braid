package braid.reviews

import java.util.Date;

import braid.User;
import braid.course.Course;

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
