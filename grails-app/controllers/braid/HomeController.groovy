package braid

import braid.course.Course;
import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class HomeController {

	def userService
	def courseService
	def dateService
	def dashboardService

	def index() {
		if (userService.currentUser.hasRole('ROLE_JEDI')) {
			redirect(action: 'dashboard')
		} else {
			redirect(action: 'announcements')
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

	@Secured([
		'ROLE_YODA',
		'ROLE_JEDI',
		'ROLE_PADAWAN',
		'ROLE_JAR_JAR'
	])
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

	@Secured([
		'ROLE_YODA',
		'ROLE_JEDI',
		'ROLE_PADAWAN',
		'ROLE_JAR_JAR'
	])
	def dashboard() {

		model: [
			homeworks: dashboardService.homeworkGraph(),
			assignments: dashboardService.assignmentGraph()
		]
	}

	@Secured([
		'ROLE_YODA',
		'ROLE_JEDI',
		'ROLE_PADAWAN',
		'ROLE_JAR_JAR'
	])
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

	@Secured([
		'ROLE_YODA',
		'ROLE_JEDI',
		'ROLE_PADAWAN',
		'ROLE_JAR_JAR'
	])
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
