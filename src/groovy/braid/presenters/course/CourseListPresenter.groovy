package braid.presenters.course

class CourseListPresenter {

	def courses
	def user
	
	boolean canCreate() {
		user.hasRole('ROLE_YODA')
	}
	
	boolean canAdmin() {
		user.hasRole('ROLE_YODA')
	}
	
	boolean canJoin() {
		!user.hasRole('ROLE_YODA')
	}

}
