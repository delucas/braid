package braid

import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.web.context.request.RequestContextHolder

import braid.course.Course

class CourseService {

	def userService

    def getCurrentCourse() {
		// should not access session inside services, but it's for the sake of encapsulation and
		// invocation clarity/consistency: courseService.currentCourse
		def currentCourseId = RequestContextHolder.currentRequestAttributes().getSession().currentCourse?.id
		Course.get(currentCourseId)?:findActiveCourseByUser()
	}

	private Course findActiveCourseByUser() {
		def currentUser = userService.currentUser
		def courseList = UserCourse.executeQuery(
			'select uc.course from UserCourse as uc where uc.user = :user and uc.course.active = true',
			[user: currentUser])
		if (!courseList.empty) {
			courseList.first() // no student should be in more than one active course
		} else {
			null // TODO: improve this
		}
	}

	def getAllCourses() {
		UserCourse.findAllByUser(userService.currentUser)*.course
	}
}
