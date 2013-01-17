package braid

class Announcement {

	String text
	User announcer
	Date dateCreated = new Date()
	
	static belongsTo = [course: Course]

	static constraints = {
		
	}
}
