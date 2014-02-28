package braid.notification

import grails.converters.JSON
import braid.Announcement;
import braid.User
import braid.assignment.AssignmentSolution

class NotificationService {

	def welcome(User user) {

		def map = [:]
		map << ['to': user.email]
		map << ['subject': "[braid] ¡Bienvenido a Braid, ${user.name}!"]
		map << ['type': 'welcome']
		map << ['model': [user: user]]

		rabbitSend 'notificationQueue', (map as JSON) as String
	}

	def informGrade(AssignmentSolution solution) {

		User user = solution.user

		def map = [:]
		map << ['to': user.email]
		map << ['subject': "[braid] Tu trabajo `${solution.assignment.title}` ha sido calificado"]
		map << ['type': 'informGrade']
		map << ['model': [user: user, solution: solution]]

		rabbitSend 'notificationQueue', (map as JSON) as String
	}

	def announce(Announcement announcement) {

		def recipients = announcement.course.members.findAll { recipient -> recipient.email }

		recipients.each { recipient ->
			def map = [:]
			map << ['to': recipient.email]
			map << ['subject': "[braid] Nuevo anuncio en la plataforma"]
			map << ['type': 'announce']
			map << ['model': [announcement: announcement, announcer: announcement.announcer]]

			rabbitSend 'notificationQueue', (map as JSON) as String
		}
	}
}
