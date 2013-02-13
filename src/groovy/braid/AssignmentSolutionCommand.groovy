package braid

import grails.validation.Validateable;

@Validateable
class AssignmentSolutionCommand {

	Boolean honorCode
	
	static constraints = {
		honorCode validator: { val -> val==true }
	}
	
}
