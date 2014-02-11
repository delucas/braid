package braid

import braid.course.Course

class UserCourse implements Serializable {

	User user
	Course course
	boolean approved = false

	static UserCourse create(User user, Course course, boolean flush = false) {
		new UserCourse(user: user, course: course).save(flush: flush, insert: true)
	}

	static boolean remove(User user, Course course, boolean flush = false) {
		UserCourse instance = UserCourse.findByUserAndCourse(user, course)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(User user) {
		executeUpdate 'DELETE FROM UserCourse WHERE user=:user', [user: user]
	}

	static void removeAll(Course course) {
		executeUpdate 'DELETE FROM UserCourse WHERE course=:course', [course: course]
	}

	static mapping = {
		id composite: ['course', 'user']
		version false
	}
}
