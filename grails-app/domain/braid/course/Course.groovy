package braid.course

import braid.UserCourse

class Course {

	String name
	String university
	String honorCode

	String syllabus

	Settings settings
	Boolean active

	static mapping = {
		honorCode type: 'text'
		syllabus type: 'text'
	}

	def getMembers() {
		UserCourse.findAllByCourse(this)*.user
	}

	def getJedis() {
		members.findAll { it -> it.hasRole('ROLE_JEDI') }
	}

}
