package braid

import grails.plugins.springsecurity.Secured
import braid.assignment.AssignmentSolution
import braid.homework.HomeworkSolution

class UserController {

	def userService
	def courseService

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def profile(Long userId) {
		def user = userService.currentUser

		if (user.hasRole('ROLE_JEDI')) {
			user = User.get(userId) ?: user
		}

		def activity = []

		def homeworkSolutions = HomeworkSolution.findAllByUser(user, [sort: 'dateCreated', order: 'desc'])
		activity += homeworkSolutions.collect {
			[type: 'homework', what: it.homework.title, when: it.dateCreated]
		}

		def assignmentSolutions = AssignmentSolution.findAllByUser(user, [sort: 'dateCreated', order: 'desc'])
		activity += assignmentSolutions.collect {
			[type: 'assignment', what: it.assignment.title, when: it.dateCreated]
		}

		model: [user: user, activity: activity.sort { a, b -> a.dateCreated <=> b.dateCreated },
			homeworkSolutions: homeworkSolutions, assignmentSolutions: assignmentSolutions]

	}

	@Secured(['ROLE_JEDI'])
	def edit() {
		def user = User.get(params.id)

		if (user.isStudentOf(userService.currentUser)) {
			model: [user: user]
		} else {
			flash.message = g.message(code: 'braid.User.edit.notAllowed')
			redirect(action: 'list')
		}
	}

	@Secured(['ROLE_JEDI'])
	def save() {
		def user = User.get(params.id)

		if (user.isStudentOf(userService.currentUser)) {
			user.properties = params
			
			// Should add some validations?
			user.save()
			
			redirect(action: 'profile', params: [userId: user.id])
		} else {
			flash.message = g.message(code: 'braid.User.edit.notAllowed')
			redirect(action: 'list')
		}
	}

	@Secured(['ROLE_JEDI'])
    def list() {

    	def me = userService.currentUser
		def course = courseService.currentCourse

		def users = findStudentsApprovedByCourse(course)

		model: [users: users.sort { a, b -> a.name <=> b.name} ]
	}

	private findStudentsApprovedByCourse(def course) {
		findStudentsByCourseAndApproved(course, true)
	}

	private findStudentsNotApprovedByCourse(def course) {
		findStudentsByCourseAndApproved(course, false)
	}

	private findStudentsByCourseAndApproved(def course, def approved) {
		UserCourse.executeQuery('select uc.user from UserCourse uc, User u, UserRole ur, Role r ' +
			'where uc.user = u and ur.user = u and ur.role = r ' +
			'and r.authority in(:roles) and uc.course = :course and uc.approved = :approved',
			[roles: ['ROLE_PADAWAN', 'ROLE_JAR_JAR'], course: course, approved: approved])
	}

	@Secured(['ROLE_JEDI'])
	def pending() {

		def course = courseService.currentCourse

		def users = findStudentsNotApprovedByCourse(course)
		if (users.size() == 0) {
			flash.message = g.message(code: 'braid.User.requests.none')
			redirect(action:'list')
		}

		render view: 'list', model: [users: users.sort { a, b -> a.name <=> b.name } ]
	}

	@Secured(['ROLE_JEDI'])
	def approve(Long userId) {

		def user = User.get(userId)
		def course = courseService.currentCourse

		def userCourse = UserCourse.findByUserAndCourse(user, course)
		userCourse.approved = true
		userCourse.save(flush: true)

		def jarjar = Role.findByAuthority('ROLE_JAR_JAR')
		UserRole.remove(user, jarjar, true)

		def padawan = Role.findByAuthority('ROLE_PADAWAN')
		UserRole.create(user, padawan, true)

		redirect(action:'pending')
	}

	@Secured(['ROLE_JEDI'])
	def reject(Long userId) {

		def user = User.get(userId)
		def course = courseService.currentCourse

		UserCourse.remove(user, course, true)

		redirect(action:'pending')
	}
}
