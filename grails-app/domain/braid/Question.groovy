package braid

class Question {

	String wording
	Integer level
	Integer position
	
	static belongsTo = [course: Course]
	static hasMany = [tags: String]
	
	static embedded = ['tags']
	
	static mapping = {
		wording type: 'text'
	}
	
	static constraints = {
		level(range:1..3)
	}

}