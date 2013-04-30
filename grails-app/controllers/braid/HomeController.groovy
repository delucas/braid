package braid

import grails.plugins.springsecurity.Secured;

class HomeController {

	def userService
	def courseService
	def dateService
	
	def index() {
		if (userService.currentUser.hasRole('ROLE_JEDI')) {
			redirect(action:'dashboard')
		} else {
			redirect(action:'announcements')
		}
	}
	
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
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def announcements() {
		
		def course = courseService.currentCourse
		def announcements = Announcement.findAllByCourse(course,
			   [sort: "dateCreated", order: "desc"])
		
		def homeworks = Homework.findAllByCourseAndDueDateGreaterThan(course, dateService.currentTime)
		def assignments = Assignment.findAllByCourseAndDueDateGreaterThan(course, dateService.currentTime)
		
		def upcomingDates = []
		upcomingDates.addAll(homeworks.collect { it ->
			[id: it.id, type: 'homework', title: it.title, dueDate: it.dueDate]
		})
		upcomingDates.addAll(assignments.collect { it ->
			[id: it.id, type: 'assignment', title: it.title, dueDate: it.dueDate]
		})
		
		model: [announcements: announcements, upcomingDates: upcomingDates.sort { a, b -> a.dueDate <=> b.dueDate }]
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def dashboard() {
		
		def homeworkGraph = HomeworkSolution.executeQuery('select h.user.name, count(h.id), sum(h.feedback.score), avg(h.feedback.score) ' +
			'from HomeworkSolution h where h.feedback.score is not null group by h.user.name')
		
		model: [homeworkGraph: homeworkGraph]
	}

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def syllabus() {
		
		def currentCourse = courseService.currentCourse
		
		model: [course: currentCourse]
	}

	@Secured(['ROLE_JEDI'])
	def saveSyllabus(String syllabus) {
		
		def currentCourse = courseService.currentCourse
		
		currentCourse.syllabus = syllabus
		currentCourse.save(flush: true)
		
		flash.msg = 'Se ha modificado el Plan de Estudios'
		
		redirect(action: 'syllabus')
	}
	
	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def honorCode() {
		
		def currentCourse = courseService.currentCourse
		
		model: [course: currentCourse]
	}
	
	@Secured(['ROLE_JEDI'])
	def saveHonorCode(String honorCode) {
		
		def currentCourse = courseService.currentCourse
		
		currentCourse.honorCode = honorCode
		currentCourse.save(flush: true)
		
		flash.msg = 'Se ha modificado el Código de Honor'
		
		redirect(action: 'honorCode')
	}

}
