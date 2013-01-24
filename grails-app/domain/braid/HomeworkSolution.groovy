package braid

class HomeworkSolution {

	String text
	Date dateCreated
	
	User user
	Homework homework
	
	static mapping = {
		text type: 'text'
	}

}