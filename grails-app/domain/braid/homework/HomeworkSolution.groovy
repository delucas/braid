package braid.homework

import braid.User;

class HomeworkSolution {

	String text
	Date dateCreated
	
	Feedback feedback
	User reviewer
	
	User user
	Homework homework
	
	static embedded = ['feedback']
	
	static constraints = {
		feedback nullable: true
		reviewer nullable: true
	}
	
	static mapping = {
		text type: 'text'
	}

}