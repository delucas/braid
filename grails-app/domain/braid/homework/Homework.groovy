package braid.homework

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

	boolean isOutOfDate() {
		dueDate < dateService.currentTime
	}

	boolean isPublished() {
		startDate < dateService.currentTime
	}

	static namedQueries = {
		alreadyPublished { currentTime ->
			le 'startDate', currentTime
		}

		byCourse { course ->
			eq 'course', course
		}

		unfinished { currentTime ->
			ge 'dueDate', currentTime
		}
	}

}