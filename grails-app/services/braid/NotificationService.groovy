package braid

import braid.assignment.AssignmentSolution;
import braid.homework.Homework;

class NotificationService {

	def mailService

	def welcome(User user) {
		mailService.sendMail {
			to user.email
			subject "¡Bienvenido a Braid, ${user.name}!"
			body( view:"/notification/welcome",
				model:[user: user])
		}
	}
}
