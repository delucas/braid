package braid.notification

import grails.converters.JSON
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
}
