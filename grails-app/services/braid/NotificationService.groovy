package braid

import braid.assignment.AssignmentSolution

class NotificationService {

	def mailService

	def welcome(User user) {
		mailService.sendMail {
			to user.email
			subject "[braid] ¡Bienvenido a Braid, ${user.name}!"
			body(view: '/notification/welcome',
				model: [user: user])
		}
	}

	def informGrade(AssignmentSolution solution) {

		User user = solution.user

		mailService.sendMail {
			to user.email
			subject "[braid] Tu trabajo `${solution.assignment.title}` ha sido calificado"
			body(view: '/notification/informGrade',
				model: [user: user, solution: solution])
		}
	}
}
