package braid.reviews

import java.util.Date;

import braid.course.Course

class CodeReviewHomework {

	def dateService

	String title
	String wording

	Date startDate
	Date solutionDueDate
	Date reviewDueDate
	Integer requiredReviews = 2

	static belongsTo = [course: Course]

	static mapping = {
		wording type: 'text'
		sort 'solutionDueDate'
	}

	static transients = ['stage', 'nextDueDate', 'dateService']

	static namedQueries = {
		alreadyPublished { currentTime ->
			le 'startDate', currentTime
		}

		byCourse { course ->
			eq 'course', course
		}

		unfinished { currentTime ->
			ge 'reviewDueDate', currentTime
		}
	}

	def getNextDueDate() {
		if (dateService.currentTime < solutionDueDate) solutionDueDate
		else reviewDueDate
	}

	def getStage() {
		if (dateService.currentTime < solutionDueDate) 'solve'
		else if (dateService.currentTime < reviewDueDate) 'review'
		else 'results'
	}

}