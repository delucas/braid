package braid

import java.awt.print.Printable;

class HomeController {

	def userService
	def courseService
	
    def registration() {
		User theUser = userService.currentUser
		if (theUser.name && theUser.dni) {
			redirect(action:'announcements')
		}
	}
	
	def finishRegistration(RegistrationCommand command) {
		if (command.validate()) {
			User theUser = userService.currentUser
			theUser.name = command.name
			theUser.dni = command.dni
			
			def theCourse = Course.get(command.courseId)
			
			UserCourse.create(theUser, theCourse)
			theUser.save(flush:true)
			
			redirect(action:'announcements')
		} else {
			render view:'registration', model: [command: command]
		}
	}
	
	def announcements() {
		def course = courseService.currentCourse
		def announcements = Announcement.findAllByCourse(course,
			   [sort: "dateCreated", order: "desc"])
		model: [announcements: announcements]
	}
	
	def syllabus() {
		
		def currentCourse = courseService.currentCourse
		
		model: [course: currentCourse]
	}
	
//	def fakeLogin(String username) {
//		User.findByUsername(username).authenticate()
//		
//		redirect(action:'announcements')
//	}
}
