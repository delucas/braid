package braid

class HomeworkSolution {

	String text
	Date dateCreated
	String feedback
	
	User user
	Homework homework
	
	static constraints = {
		feedback nullable: true
	}
	
	static mapping = {
		text type: 'text'
		feedback type: 'text'
	}

}