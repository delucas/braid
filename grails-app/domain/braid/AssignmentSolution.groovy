package braid

class AssignmentSolution {

	String commit
	Double score = 0.0
	String feedback = 'Sin detalles por el momento'
	Date dateCreated //TODO: cambiar este y el de HS por submissionDate
	
	User user
	Assignment assignment

	static constraints = {
		score nullable: true
		feedback nullable: true
		commit nullable: true
	}
	
	static mapping = {
		feedback(type: "text")
	}

}