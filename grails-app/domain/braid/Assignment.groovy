package braid

import java.util.Date;

import braid.course.Course;
import braid.github.Repository;

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

	boolean getOutOfDate() {
		dueDate < dateService.currentTime
	}

	def getWording() {
		new URL(repo.readmeUrl).getText('UTF-8')
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