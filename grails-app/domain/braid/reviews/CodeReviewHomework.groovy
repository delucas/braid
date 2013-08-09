package braid.reviews

import braid.course.Course;

class CodeReviewHomework {

	String title
	String wording
	Date solutionDueDate
	Date reviewDueDate
	Integer requiredReviews = 2

	static belongsTo = [course: Course]

	static mapping = {
		wording type: 'text'
	}

}