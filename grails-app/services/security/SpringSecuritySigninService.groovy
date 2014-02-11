package security

import org.codehaus.groovy.grails.plugins.springsecurity.GormUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import braid.User

class SpringSecuritySigninService extends GormUserDetailsService {

	void signIn(User user) {
		def authorities = loadAuthorities(user, user.username, true)
		def userDetails = createUserDetails(user, authorities)
		SecurityContextHolder.context.setAuthentication(
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities))
	}
}
