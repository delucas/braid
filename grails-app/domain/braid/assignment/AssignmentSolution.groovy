package braid.assignment

import braid.User

class AssignmentSolution {

	String commit
	
	Double score
	String feedback
	Date dateCreated //TODO: cambiar este y el de HS por submissionDate
	
	User user
	Assignment assignment

	static constraints = {
		score nullable: true
		feedback nullable: true
		commit nullable: true
	}
	
	static mapping = {
		feedback(type: 'text')
	}
	
	static transients = ['graded']
	
	boolean isGraded() {
		feedback != null
	}

}