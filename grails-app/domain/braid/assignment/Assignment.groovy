package braid.assignment

import braid.course.Course
import braid.github.Repository

class Assignment {

	def dateService

	String title
	Repository repo

	Date startDate
	Date dueDate

	static belongsTo = [course: Course]

	static mapping = {
		sort 'dueDate'
	}

	static transients = ['outOfDate', 'dateService', 'wording']
	static embedded = ['repo']

	boolean isOutOfDate() {
		dueDate < dateService.currentTime
	}

	boolean isPublished() {
		startDate < dateService.currentTime
	}

	def getWording() {
		new URL(repo.readmeUrl).getText('UTF-8')
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