package braid

import grails.validation.Validateable;

@Validateable
class QuestionCommand {

	String wording
	String tags
	Integer level
	
	static constraints = {
		wording blank: false
		tags blank: false
		level(range:1..3)
	}
	
}
