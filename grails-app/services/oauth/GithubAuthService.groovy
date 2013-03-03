package oauth

import grails.converters.deep.JSON
import org.scribe.model.OAuthRequest
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.model.Verifier
import org.scribe.oauth.OAuthService

class GithubAuthService extends GrailsOAuthService {

    @Override
    OAuthService createOAuthService(String callbackUrl) {
        return createServiceBuilder(GitHubApi,
                    grailsApplication.config.auth.github.key as String,
                    grailsApplication.config.auth.github.secret as String,
                    callbackUrl).build()
    }

    AuthInfo getAuthInfo(String callbackUrl) {
        OAuthService authService = createOAuthService(callbackUrl)
		new AuthInfo(authUrl : authService.getAuthorizationUrl(null), requestToken: null, service: authService)
	}

    Token getAccessToken(OAuthService authService, Map params, Token requestToken) {
		Verifier verifier = new Verifier(params.code as String)
		authService.getAccessToken(requestToken, verifier)
	}

    OAuthProfile getProfile(OAuthService authService, Token accessToken) {
        OAuthRequest request = new OAuthRequest(Verb.GET, 'https://api.github.com/user')
        authService.signRequest(accessToken, request)
        def response = request.send()

        def user = JSON.parse(response.body)
        new OAuthProfile(username: user.login, uid: user.id, email: user.email,picture:user.picture)
    }
}