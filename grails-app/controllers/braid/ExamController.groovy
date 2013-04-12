package braid

import grails.plugins.springsecurity.Secured

class ExamController {

	@Secured(['ROLE_YODA', 'ROLE_JEDI', 'ROLE_PADAWAN', 'ROLE_JAR_JAR'])
	def list() {
		
	}
	
}
