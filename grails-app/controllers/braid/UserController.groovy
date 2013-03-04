package braid


class UserController {

	def userService
	def courseService
	
	def profile(Long userId) {
		User user = userService.currentUser
		
		if(user.hasRole('JEDI') || user.hasRole('YODA')) {
			user = User.get(userId)?:user
		}
		
		model: [user: user]
		
	}
	
    def list() {
		
    	def me = userService.currentUser
		def course = courseService.currentCourse
		
		def users = findStudentsApprovedByCourse(course)
		
		if(me.hasRole('YODA')) {
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
			[roles: ['PADAWAN', 'JAR_JAR'], course: course, approved: approved])
	}
	
	def pending() {

		def course = courseService.currentCourse
		
		def users = findStudentsNotApprovedByCourse(course)
		if(users.size() == 0) {
			flash.message = 'No hay solicitudes de alumno pendientes de aceptación. Se mostrará el listado completo.'
			redirect(action:'list')
		}
		
		render view: 'list', model: [users: users.sort {a,b -> a.name <=> b.name}]
	}
	
	def approve(Long userId) {
		
		def user = User.get(userId)
		def course = courseService.currentCourse
		
		def userCourse = UserCourse.findByUserAndCourse(user, course)
		userCourse.approved = true
		userCourse.save(flush: true)
		
		def jarjar = Role.findByAuthority('JAR_JAR')
		UserRole.remove(user, jarjar, true)

		def padawan = Role.findByAuthority('PADAWAN')
		UserRole.create(user, padawan, true)
		
		redirect(action:'pending')
	}
	
	def reject(Long userId) {
		
		def user = User.get(userId)
		def course = courseService.currentCourse
		
		UserCourse.remove(user, course, true)
		
		user.dni = null
		user.name = null
		user.save(flush: true)
		
		redirect(action:'pending')
	}
	
}
