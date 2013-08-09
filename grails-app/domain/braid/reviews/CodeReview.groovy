package braid.reviews

import java.util.Date;

import braid.User;
import braid.course.Course;

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
