package braid.notification

import grails.converters.JSON
import braid.assignment.AssignmentSolution

class NotificationSenderService {

	static transactional = true

	def grailsApplication
	def mailService

	final static rabbitQueue = 'notificationQueue'

	void handleMessage(message) {
		def returnedMap = JSON.parse(new String(message))

		mailService.sendMail {
			to returnedMap.get('to')
			subject returnedMap.get('subject')
			body(view: "/notification/${returnedMap.get('type')}",
				model: returnedMap.get('model'))
		}
	}
}
