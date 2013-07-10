package braid

import braid.course.Course;
import braid.github.Repository;

class Assignment {

	def dateService
	
	String title
	Repository repo
	Date dueDate
	
	static belongsTo = [course: Course]
	
	static transients = ['outOfDate', 'dateService', 'wording']
	static embedded = ['repo']
	
	boolean getOutOfDate() {
		dueDate < dateService.currentTime
	}

	def getWording() {
		new URL(repo.readmeUrl).getText('UTF-8')
	}
	
}