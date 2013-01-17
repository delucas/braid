package braid

import java.awt.print.Printable;

class HomeController {

	def courseService
	
    def setup() {
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
