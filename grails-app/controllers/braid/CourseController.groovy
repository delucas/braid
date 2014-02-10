package braid

import grails.plugins.springsecurity.Secured
import grails.validation.Validateable
import braid.course.Course
import braid.course.Settings
import braid.presenters.course.CourseListPresenter

class CourseController {

	def userService
	def courseService

	def joinCourseService
	def promoteUserService

	@Secured([
		'ROLE_YODA',
		'ROLE_JEDI',
		'ROLE_PADAWAN',
		'ROLE_JAR_JAR'
	])
	def list() {

		def course = courseService.currentCourse
		if (course) {
			redirect(controller: 'home', action: 'index')
		} else {
			[presenter: new CourseListPresenter(
				courses: Course.findAllActive(),
				user: userService.currentUser)
			]
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

	@Secured(['ROLE_YODA'])
	def create(CourseSettingsCommand command) {
		if (request.method == 'GET') {
		} else {

			if (command.validate()) {
				// TODO: migrate to a service
				def course = new Course().with {
					name = command.name
					university = command.university
					active = true
					settings = new Settings().with {
						githubUsername = command.githubUsername
						bannerColor = command.bannerColor
						it
					}
					it
				}

				course.settings.save(flush: true)
				course.save(flush: true)

				redirect(action: 'list')
			} else {
				[command: command]
			}
		}
	}

	@Secured(['ROLE_YODA'])
	def students(Long id) {
		def course = Course.get(id)
		def members = course.members.sort { a, b -> b.hasRole('ROLE_JEDI') <=> a.hasRole('ROLE_JEDI')}
		def jedis = course.jedis

		[presenter: [course: course, students: members, jedis: jedis]]
	}

	@Secured(['ROLE_YODA'])
	def promote(Long userId, Long courseId) {

		changeDegree(userId, courseId) { user, course ->
			promoteUserService.promote(user, course)
		}
	}

	@Secured(['ROLE_YODA'])
	def demote(Long userId, Long courseId) {

		changeDegree(userId, courseId) { user, course ->
			promoteUserService.demote(user, course)
		}
	}

	private changeDegree(userId, courseId, executeChange) {
		def user = User.get(userId)
		def course = Course.get(courseId)

		executeChange(user, course)
		redirect(action: 'students', id: courseId)
	}

	@Secured(['ROLE_JEDI'])
	def config(CourseSettingsCommand command) {
		def course = courseService.currentCourse
		if (request.method == 'GET') {
			[course: course, command: course]
		} else {

			if (command.validate()) {
				// TODO: migrate to a service
				course.name = command.name
				course.university = command.university

				def options = 0
				params.list('elements').each { options += it as Integer }
				course.settings.options = options
						course.settings.githubUsername = command.githubUsername
				course.settings.bannerColor = command.bannerColor

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
	String bannerColor
	String githubUsername

	static constraints = {
		name blank: false
		university blank: false
		bannerColor blank: false
		githubUsername blank: false
	}

	// To conform Course interface
	def getSettings() {
		this
	}
}
