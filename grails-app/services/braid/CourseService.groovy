package braid

import org.springframework.web.context.request.RequestContextHolder

import braid.course.Course

class CourseService {

	def userService

    def getCurrentCourse() {
		// should not access session inside services, but it's for the sake of encapsulation and
		// invocation clarity/consistency: courseService.currentCourse
		def currentCourseId = RequestContextHolder.currentRequestAttributes().getSession().currentCourse?.id
		Course.get(currentCourseId)?:UserCourse.findAllByUser(userService.currentUser)[0].course
	}

	def getAllCourses() {
		UserCourse.findAllByUser(userService.currentUser)*.course
	}
}
