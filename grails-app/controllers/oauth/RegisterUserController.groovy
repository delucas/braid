package oauth

import org.springframework.security.authentication.BadCredentialsException

import security.SpringSecuritySigninService
import braid.Course
import braid.RegistrationCommand
import braid.Role
import braid.User
import braid.UserCourse
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

    def save = {
        def user = new User(params)
		user.password = new Date().time.toString()
        def profile = session["${params.oauthProvider}_profile"] as OAuthProfile
        def originalUrl = session["${params.oauthProvider}_originalUrl"]

        if (!profile || !session["${params.oauthProvider}_authToken"]) {
            log.warn("No profile or authToken found")
            throw new BadCredentialsException("No profile or authToken found")
        }

        user.oauthId = profile.uid
        user.avatarUrl = profile.picture
		log.info("url del avatar ${profile.picture}")
		def jarjar = Role.findByAuthority('JAR_JAR')
		
        user.save(failOnError: true)

		UserRole.create(user, jarjar, true)
        springSecuritySigninService.signIn(user)

		redirect (controller:'home', action:'announcements')
    }
	
	
	def finishRegistration(RegistrationCommand command) {
		
		
		if (command.validate()) {
			
			
			def profile = session["${params.oauthProvider}_profile"] as OAuthProfile
			def originalUrl = session["${params.oauthProvider}_originalUrl"]
	
			if (!profile || !session["${params.oauthProvider}_authToken"]) {
				log.warn("No profile or authToken found")
				throw new BadCredentialsException("No profile or authToken found")
			}
	
			def theUser = new User()
			theUser.password = new Date().time.toString()
			theUser.name = command.name
			theUser.dni = command.dni
			theUser.username = command.username
			theUser.oauthId = profile.uid
			theUser.avatarUrl = profile.picture
			log.error("url del avatar ${profile.picture}")
			theUser.save(failOnError: true)
			
			
			def jarjar = Role.findByAuthority('JAR_JAR')
	
			UserRole.create(theUser, jarjar, true)
			springSecuritySigninService.signIn(theUser)
			
			
			def theCourse = Course.get(command.courseId)
			
			UserCourse.create(theUser, theCourse)
			theUser.save(flush:true)
			
			redirect(action:'announcements')
		} else {
			render view:'register', model: [command: command]
		}
	}

}