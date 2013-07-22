package braid.course

import braid.Role
import braid.UserCourse
import braid.UserRole

class JoinCourseService {

	def userService

    def joinCourse(Course course){
		def currentUser = userService.currentUser

		assignMinimalRole(currentUser)

		UserCourse.create(currentUser, course, true)
	}

	private assignMinimalRole(user) {
		def padawan = Role.findByAuthority('ROLE_PADAWAN')
		UserRole.remove(user, padawan, true)

		def jarjar = Role.findByAuthority('ROLE_JAR_JAR')
		if (! UserRole.findByUserAndRole(user, jarjar)) {
			UserRole.create(user, jarjar, true)
		}
	}
}
