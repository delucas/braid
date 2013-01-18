package braid

import grails.validation.Validateable;

@Validateable
class RegistrationCommand {

	String name
	String dni
	Integer courseId
	
	static constraints = {
		name blank: false
		dni blank: false
		courseId validator: { val -> Course.get(val) != null }
	}
	
}
