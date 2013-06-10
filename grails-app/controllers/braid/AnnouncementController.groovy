package braid

import grails.plugins.springsecurity.Secured


class AnnouncementController {

	def userService
	def courseService

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
    def list() {
		def course = courseService.currentCourse
		def announcements = Announcement.findAllByCourse(course,
               [sort: 'dateCreated', order: 'desc'])
		model: [announcements: announcements]
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI'])
	def post() {
		def ann = new Announcement(text: params.text,
			course: courseService.currentCourse,
			announcer: userService.currentUser)
		ann.save()
		
		redirect(controller:'home', action:'announcements')
	}
	
}
