package braid

import braid.course.Course

class Homework {

	def dateService

	String title
	String wording

	Date startDate
	Date dueDate

	static belongsTo = [course: Course]

	static mapping = {
		wording type: 'text'
		sort 'dueDate'
	}

	static transients = ['outOfDate', 'published', 'dateService']

	boolean getOutOfDate() {
		dueDate < dateService.currentTime
	}

	boolean isPublished() {
		startDate < dateService.currentTime
	}

	static namedQueries = {
		alreadyPublished { Date currentTime ->
			le 'startDate', currentTime
		}

		byCourse { course ->
			eq 'course', course
		}
	}

}