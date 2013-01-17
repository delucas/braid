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
}
