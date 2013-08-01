package oauth

import org.springframework.security.authentication.BadCredentialsException

import security.SpringSecuritySigninService
import braid.RegistrationCommand
import braid.Role
import braid.User
import braid.UserCourse
import braid.UserRole
import braid.course.Course;

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
			theUser.name = command.name
			theUser.dni = command.dni
			theUser.username = command.username
			theUser.oauthProvider = command.oauthProvider

			theUser.email = profile.email
			theUser.oauthId = profile.uid
			theUser.avatarUrl = profile.picture
			theUser.save(failOnError: true)
			
			
			def jarjar = Role.findByAuthority('ROLE_JAR_JAR')
	
			UserRole.create(theUser, jarjar, true)
			springSecuritySigninService.signIn(theUser)
			
			theUser.save(flush:true)
			log.error(theUser.errors)
			def theCourse = Course.get(command.courseId)
			
			UserCourse.create(theUser, theCourse)

			redirect (controller:'home', action:'announcements')

		} else {

			render view:'register', model: [command: command]

		}
	}

}
