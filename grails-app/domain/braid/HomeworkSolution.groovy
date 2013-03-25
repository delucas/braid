package braid

class HomeworkSolution {

	String text
	Date dateCreated
	Feedback feedback
	
	User user
	Homework homework
	
	static embedded = ['feedback']
	
	static constraints = {
		feedback nullable: true
	}
	
	static mapping = {
		text type: 'text'
	}

}