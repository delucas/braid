package braid

import braid.course.Course;
import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class CourseController {

	def userService
	def joinCourseService

	def list() {
		[courses: Course.findAllActive()]
	}

	def join(Long id) {

		Course course = Course.get(id)
		joinCourseService.joinCourse(course)

		redirect (controller: 'home')
	}

	def select(Long id) {

		UserCourse uc = UserCourse.findByUserAndCourse(userService.currentUser, Course.get(id))
		session.currentCourse = uc.course

		redirect (controller: 'home', action: 'dashboard')
	}
}
