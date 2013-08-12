package braid.reviews

import braid.course.Course

class CodeReviewHomework {

	def dateService

	String title
	String wording
	Date solutionDueDate
	Date reviewDueDate
	Integer requiredReviews = 2

	static belongsTo = [course: Course]

	static mapping = {
		wording type: 'text'
	}

	static transients = ['stage', 'dateService']

	def getStage() {
		if (dateService.currentTime < solutionDueDate) 'solve'
		else if (dateService.currentTime < reviewDueDate) 'review'
		else 'results'
	}

}