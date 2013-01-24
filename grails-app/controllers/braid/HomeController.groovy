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
		
		def homeworks = Homework.findAllByCourseAndDueDateGreaterThan(course, new Date())
		def upcomingDates = []
		upcomingDates.addAll(homeworks.collect { it ->
			[id: it.id, type: 'homework', title: it.title, dueDate: it.dueDate]
		})
		
		model: [announcements: announcements, upcomingDates: upcomingDates.sort { a, b -> a.dueDate <=> b.dueDate }]
	}
	
	def syllabus() {
		
		def currentCourse = courseService.currentCourse
		
		model: [course: currentCourse]
	}
	
	def honorCode() {
		def currentCourse = courseService.currentCourse
		
		model: [course: currentCourse]
	}

}
