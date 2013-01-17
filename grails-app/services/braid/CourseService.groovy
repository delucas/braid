package braid

class CourseService {

	def userService
	
    def getCurrentCourse() {
		User currentUser = userService.currentUser
		// TODO: resolver cuando sea multicurso
		Course.list().asList()[0]
	}
}
