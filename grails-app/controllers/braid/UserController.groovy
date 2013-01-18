package braid


class UserController {

	def userService
	def courseService
	
	def profile(Long userId) {
		User user = userService.currentUser
		
		if(user.hasRole('JEDI')) {
			user = User.get(userId)?:user
		}
		
		model: [user: user]
		
	}
	
    def list(Long courseId) {
		
    	def me = userService.currentUser
		def course = courseService.currentCourse
		
		def users = UserCourse.findAllByCourse(course).collect { uc -> uc.user }
		
		if(me.hasRole('YODA')) {
			users = User.list()
		}
		
		model: [users: users.sort {a,b -> a.name <=> b.name}]
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
		
		redirect(action:'list')
	}
	
	def reject(Long userId) {
		
		def user = User.get(userId)
		def course = courseService.currentCourse
		
		UserCourse.remove(user, course, true)
		
		user.dni = null
		user.name = null
		user.save(flush: true)
		
		redirect(action:'list')
	}
	
}
