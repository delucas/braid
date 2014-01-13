package braid

import braid.assignment.AssignmentSolution;
import braid.homework.HomeworkSolution;
import grails.plugins.springsecurity.Secured


class UserController {

	def userService
	def courseService

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def profile(Long userId) {
		def user = userService.currentUser

		if(user.hasRole('ROLE_JEDI') || user.hasRole('ROLE_YODA')) {
			user = User.get(userId)?:user
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

		if (!user.isStudentOf(userService.currentUser)) {
			flash.message = 'No tienes permisos para editar a este estudiante'
			redirect(action: 'list')
		} else {
			model: [user: user]
		}
	}

	@Secured(['ROLE_JEDI'])
	def save() {
		def user = User.get(params.id)

		if (!user.isStudentOf(userService.currentUser)) {
			flash.message = 'No tienes permisos para editar a este estudiante'
			redirect(action: 'list')
		} else {
			user.properties = params

			// Should add some validations?
			user.save()

			redirect(action: 'profile', params: [userId: user.id])
		}
	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI'])
    def list() {

    	def me = userService.currentUser
		def course = courseService.currentCourse

		def users = findStudentsApprovedByCourse(course)

		if(me.hasRole('ROLE_YODA')) {
			users = User.list()
		}

		model: [users: users.sort {a,b -> a.name <=> b.name}]
	}

	private findStudentsApprovedByCourse(def course) {
		findStudentsByCourseAndApproved(course, true)
	}

	private findStudentsNotApprovedByCourse(def course) {
		findStudentsByCourseAndApproved(course, false)
	}

	private findStudentsByCourseAndApproved(def course, def approved) {
		UserCourse.executeQuery("select uc.user from UserCourse uc, User u, UserRole ur, Role r " +
			"where uc.user = u and ur.user = u and ur.role = r " +
			"and r.authority in(:roles) and uc.course = :course and uc.approved = :approved",
			[roles: ['ROLE_PADAWAN', 'ROLE_JAR_JAR'], course: course, approved: approved])
	}

	@Secured(['ROLE_JEDI'])
	def pending() {

		def course = courseService.currentCourse

		def users = findStudentsNotApprovedByCourse(course)
		if(users.size() == 0) {
			flash.message = 'No hay solicitudes de alumno pendientes de aceptación. Se mostrará el listado completo.'
			redirect(action:'list')
		}

		render view: 'list', model: [users: users.sort {a,b -> a.name <=> b.name}]
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
