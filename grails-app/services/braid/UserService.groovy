package braid

class UserService {

	def springSecurityService
	
    def getCurrentUser() {
		User.findByUsername(springSecurityService.principal.username)
	}
	
	def getCurrentTimeZone() {
		TimeZone.getTimeZone('America/Argentina/Buenos_Aires')
	}
}
