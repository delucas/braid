package braid

import grails.plugins.springsecurity.Secured


class AnnouncementController {

	def userService
	def courseService
	def notificationService

	@Secured(['ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
    def list() {
		def course = courseService.currentCourse
		def announcements = Announcement.findAllByCourse(course,
               [sort: 'dateCreated', order: 'desc'])
		model: [announcements: announcements]
	}
	
	@Secured(['ROLE_JEDI'])
	def post() {
		def ann = new Announcement(text: params.text,
			course: courseService.currentCourse,
			announcer: userService.currentUser)
		ann.save()

		notificationService.announce(ann)

		redirect(controller:'home', action:'announcements')
	}
	
}
