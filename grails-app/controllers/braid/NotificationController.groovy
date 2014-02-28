package braid

import braid.assignment.AssignmentSolution;
import grails.plugins.springsecurity.Secured

class NotificationController {

	def userService

	@Secured(['ROLE_JEDI'])
	def index() {
		render 'types: test?type=welcome, test?type=informGrade, test?type=announce'
	}
	
	@Secured(['ROLE_JEDI'])
	def test() {
		if (params.type) {
			"render${params.type}"()
		} else {
			redirect(action: 'index')
		}
	}

	private renderwelcome() {
		render view: '/notification/welcome',
		model:[user: userService.currentUser]
	}
	
	private renderinformGrade() {
		AssignmentSolution solution = AssignmentSolution.list().first()
		render view: '/notification/informGrade',
		model: [
			solution: solution,
			user: solution.user
		]
	}
	private renderannounce() {
		Announcement ann = Announcement.list().first()
		render view: '/notification/announce',
		model: [
			announcement: ann,
			announcer: ann.announcer
		]
	}
}
