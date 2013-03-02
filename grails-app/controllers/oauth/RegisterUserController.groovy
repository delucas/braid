package oauth

import org.springframework.security.authentication.BadCredentialsException

import security.SpringSecuritySigninService
import braid.User

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
        def profile = session["${params.oauthProvider}_profile"] as OAuthProfile
        def originalUrl = session["${user.oauthProvider}_originalUrl"]

        if (!profile || !session["${user.oauthProvider}_authToken"]) {
            log.warn("No profile or authToken found")
            throw new BadCredentialsException("No profile or authToken found")
        }

        user.oauthId = profile.uid
        user.avatarUrl = profile.picture

		//agregar roles
		
        user.save(failOnError: true)

        springSecuritySigninService.signIn(user)

        redirect(uri: (originalUrl ?: '/') - request.contextPath)
    }

}