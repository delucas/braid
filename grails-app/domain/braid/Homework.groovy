package braid

import braid.course.Course;

class Homework {

	def dateService
	
	String title
	String wording
	Date dueDate
	
	static belongsTo = [course: Course]
	
	static mapping = {
		wording type: 'text'
	}
	
	static transients = ['outOfDate', 'dateService']
	
	boolean getOutOfDate() {
		dueDate < dateService.currentTime
	}

}