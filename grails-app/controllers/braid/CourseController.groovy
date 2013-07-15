package braid

import braid.course.Course;
import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class CourseController {

	def userService
	
	def select(Long id) {
		
		UserCourse uc = UserCourse.findByUserAndCourse(userService.currentUser, Course.get(id))
		session.currentCourse = uc.course
		
		redirect (controller: 'home', action: 'dashboard')
	}
}
