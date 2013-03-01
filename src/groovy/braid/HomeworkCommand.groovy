package braid

import java.util.Date;

import grails.validation.Validateable;

@Validateable
class HomeworkCommand {

	String title
	String wording
	Date dueDate
	
	static constraints = {
		title blank: false
		wording blank: false
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
