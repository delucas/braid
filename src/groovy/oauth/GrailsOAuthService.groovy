package oauth

import org.scribe.builder.ServiceBuilder
import org.scribe.model.Token
import org.scribe.oauth.OAuthService

abstract class GrailsOAuthService {

	static transactional = false

	def grailsApplication

	@Override
	ServiceBuilder createServiceBuilder(Class provider, String apiKey, String secretKey, String callbackUrl) {
		ServiceBuilder builder = new ServiceBuilder().provider(provider)
				.apiKey(apiKey).apiSecret(secretKey)
				.callback(callbackUrl)
		builder
	}

	abstract AuthInfo getAuthInfo(String callbackUrl)

	abstract Token getAccessToken(OAuthService authService, Map params, Token requestToken)

	abstract OAuthProfile getProfile(OAuthService authService, Token accessToken)
}

class AuthInfo implements Serializable {
	OAuthService service
	String authUrl
	Token requestToken
}
