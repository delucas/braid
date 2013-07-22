package braid.course

class Course {

	String name
	String university
	String honorCode

	String syllabus

	Settings settings
	Boolean active

	static constraints = {
	}

	static mapping = {
		honorCode type: 'text'
		syllabus type: 'text'
	}

}
