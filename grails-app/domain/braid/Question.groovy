package braid

class Question {

	String wording
	Integer level
	
	static belongsTo = [course: Course]

	static constraints = {
		level(range:1..3)
	}
}
