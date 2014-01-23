package braid

import java.util.Date;

import grails.plugins.springsecurity.Secured
import grails.validation.Validateable;
import braid.course.Course

class CourseController {

	def userService
	def courseService

	def joinCourseService

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def list() {

		def course = courseService.currentCourse
		if (course) {
			redirect(controller: 'home', action: 'index')
		} else {
			[courses: Course.findAllActive()]
		}
	}

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def join(Long id) {

		Course course = Course.get(id)
		joinCourseService.joinCourse(course)

		redirect (controller: 'home')
	}

	@Secured(['ROLE_JEDI'])
	def select(Long id) {

		UserCourse uc = UserCourse.findByUserAndCourse(userService.currentUser, Course.get(id))
		session.currentCourse = uc.course

		redirect (controller: 'home', action: 'dashboard')
	}

	@Secured(['ROLE_JEDI'])
	def config(CourseSettingsCommand command) {
		def course = courseService.currentCourse
		if (request.method == 'GET') {
			[course: course, command: course]
		} else {

			if (command.validate()) {
				// TODO: migrate to a service
				course.name = params.name
				course.university = params.university

				def options = 0
				params.list('elements').each { options += it as Integer }
				course.settings.options = options

				redirect(controller: 'home', action: 'dashboard')
			} else {
				[course: course, command: command]
			}
		}
	}

}

@Validateable
class CourseSettingsCommand {

	String name
	String university

	static constraints = {
		name blank: false
		university blank: false
	}

}