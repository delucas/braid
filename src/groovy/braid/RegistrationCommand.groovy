package braid

import grails.validation.Validateable;

@Validateable
class RegistrationCommand {

	String name
	Integer dni
	Integer courseId
	String username
	String oauthProvider
	
	static constraints = {
		
		name blank: false
		dni blank: false
		courseId validator: { val -> Course.get(val) != null }
		oauthProvider nullable:true
	}
	
}
