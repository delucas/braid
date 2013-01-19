package braid

class Course {

	String name
	String university
	String honorCode
	
	String syllabus

	static constraints = {
	}
	
	static mapping = {
		honorCode type: 'text'
		syllabus type: 'text'
	}
}
