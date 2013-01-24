package braid

class Homework {

	String title
	String wording
	Date dueDate
	
	static belongsTo = [course: Course]
	
	static mapping = {
		wording type: 'text'
	}
	
	static transients = ['outOfDate']
	
	boolean getOutOfDate() {
		dueDate < new Date()
	}

}