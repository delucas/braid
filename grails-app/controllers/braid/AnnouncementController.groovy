package braid


class AnnouncementController {

	def userService
	def courseService
	
    def list() {
		def course = courseService.currentCourse
		def announcements = Announcement.findAllByCourse(course,
               [sort: "dateCreated", order: "desc"])
		model: [announcements: announcements]
	}
	
	def post() {
		def ann = new Announcement(text: params.text,
			course: courseService.currentCourse, announcer: userService.currentUser)
		ann.save()
		
		redirect(controller:'home', action:'announcements')
	}
	
}
