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

    def save = {
        def user = new User(params)
		user.password = new Date().time.toString()
        def profile = session["${params.oauthProvider}_profile"] as OAuthProfile
        def originalUrl = session["${user.oauthProvider}_originalUrl"]

        if (!profile || !session["${user.oauthProvider}_authToken"]) {
            log.warn("No profile or authToken found")
            throw new BadCredentialsException("No profile or authToken found")
        }

        user.oauthId = profile.uid
        user.avatarUrl = profile.picture

		def jarjar = Role.findByAuthority('JAR_JAR')
		
        user.save(failOnError: true)

		UserRole.create(user, jarjar, true)
        springSecuritySigninService.signIn(user)

		redirect (controller:'home', action:'announcements')
    }

}