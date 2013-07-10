package braid

import braid.course.Course;

class CourseService {

	def userService

    def getCurrentCourse() {
		// TODO: por ahora no solapan: es 1 a 1
		UserCourse.findByUser(userService.currentUser).course
	}
}
