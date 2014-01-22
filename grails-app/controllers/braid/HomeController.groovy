package braid

import grails.plugins.springsecurity.Secured
import braid.assignment.Assignment
import braid.homework.Homework
import braid.reviews.CodeReviewHomework

class HomeController {

	def userService
	def courseService
	def dateService
	def dashboardService

	def index() {
		def course = courseService.currentCourse

		if (course) {
			if (userService.currentUser.hasRole('ROLE_JEDI')) {
				redirect(action: 'dashboard')
			} else {
				redirect(action: 'announcements')
			}
		} else {
			redirect(controller: 'course', action: 'list')
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
		def currentTime = dateService.currentTime
		def announcements = Announcement.findAllByCourse(course,
				[sort: 'dateCreated', order: 'desc'])

		def homeworks = Homework.alreadyPublished(currentTime).unfinished(currentTime).byCourse(course).list()
		def assignments = Assignment.alreadyPublished(currentTime).unfinished(currentTime).byCourse(course).list()
		def reviews = CodeReviewHomework.alreadyPublished(currentTime).unfinished(currentTime).byCourse(course).list()

		def upcomingDates = []
		upcomingDates.addAll(homeworks.collect { it ->
			[id: it.id, type: 'homework', title: it.title, dueDate: it.dueDate]
		})
		upcomingDates.addAll(assignments.collect { it ->
			[id: it.id, type: 'assignment', title: it.title, dueDate: it.dueDate]
		})
		upcomingDates.addAll(reviews.collect { it ->
			[id: it.id, type: 'codeReview', title: it.title, dueDate: it.nextDueDate]
		})

		model: [announcements: announcements, upcomingDates: upcomingDates.sort { a, b -> a.dueDate <=> b.dueDate } ]
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
