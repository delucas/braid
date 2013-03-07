package braid

import java.util.Date;

import braid.github.Repository;

import grails.validation.Validateable;

@Validateable
class AssignmentCommand {

	String title
	String repoName
	Date dueDate
		
	static constraints = {
		title blank: false
		repoName blank: false
		dueDate validator: { val ->
			val.after(new Date())
		}
	}
	
	def getCurrentTime() {
		// TODO: preguntar a Mati cómo inyectar en una clase el servicio
		// dateService, porque esto no debería ser así
		return new Date()
	}
	
}
