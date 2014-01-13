package braid.homework

class Feedback {

	String text
	Integer score
	
	static mapping = {
		text type: 'text'
	}
	
	static constraints = {
		score range: 1..3
	}

}