package oauth

import org.springframework.security.authentication.BadCredentialsException

import security.SpringSecuritySigninService
import braid.Role
import braid.User
import braid.UserRole

class RegisterUserController {

    SpringSecuritySigninService springSecuritySigninService

    def register = {
        def user = new User()
        def profile = session["${params.provider}_profile"]

        if (!profile || !session["${params.provider}_authToken"]) {
            log.warn("No profile or authToken found")
            return redirect(controller: "error", action: 'forbidden')
        }

        user.username = profile.username
        user.email = profile.email
        user.oauthProvider = params.provider

        render(view: "register", model: [user: user, provider: params.provider])
    }

	def finishRegistration(RegistrationCommand command) {


		if (command.validate()) {


			def profile = session["${params.oauthProvider}_profile"] as OAuthProfile

			if (!profile || !session["${params.oauthProvider}_authToken"]) {
				log.warn("No profile or authToken found")
				throw new BadCredentialsException("No profile or authToken found")
			}

			def theUser = new User()
			theUser.password = new Date().time.toString()
			theUser.username = command.username
			theUser.name = command.name
			theUser.dni = command.dni
			theUser.email = command.email
			theUser.oauthProvider = command.oauthProvider

			theUser.oauthId = profile.uid
			theUser.avatarUrl = profile.picture
			theUser.save(failOnError: true)


			def jarjar = Role.findByAuthority('ROLE_JAR_JAR')

			UserRole.create(theUser, jarjar, true)
			springSecuritySigninService.signIn(theUser)

			theUser.save(flush:true)
			log.error(theUser.errors)

			redirect(controller: 'course', action: 'list')

		} else {

			render view:'register', model: [command: command]

		}
	}

}

class RegistrationCommand {

	String name
	Integer dni
	String email
	String username
	String oauthProvider

	static constraints = {
		name blank: false
		dni blank: false
		email blank: false, email: true
		oauthProvider nullable: true
	}

}
