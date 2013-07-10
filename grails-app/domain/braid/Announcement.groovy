package braid

import braid.course.Course;

class Announcement {

	String text
	User announcer
	Date dateCreated
	
	static belongsTo = [course: Course]

	static mapping = {
		text type: 'text'
	}
		
}
