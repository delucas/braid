package braid

class UserService {

	def springSecurityService
	
    def getCurrentUser() {
		User.findByUsername(springSecurityService.principal.username)
	}
}
