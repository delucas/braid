package braid

class Announcement {

	String text
	User announcer
	Date dateCreated
	
	static belongsTo = [course: Course]

	static constraints = {
		
	}
}
