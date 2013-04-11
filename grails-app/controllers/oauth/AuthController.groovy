package oauth

import grails.util.GrailsUtil
import security.SpringSecuritySigninService
import braid.User


class AuthController {

    SpringSecuritySigninService springSecuritySigninService

    def signin = {
		
		if (GrailsUtil.environment == 'development') {
			
			redirect(controller:'githubMock')
			
		}else{
	        GrailsOAuthService service = resolveService(params.provider)
	        if (!service) {
	            redirect(url: '/')
	        }
	
	        session["${params.provider}_originalUrl"] = params.originalUrl
	
	        def callbackParams = [provider: params.provider]
	        def callback = "${createLink(action: 'callback', absolute: 'true', params: callbackParams)}"
	        def authInfo = service.getAuthInfo(callback)
			
			println "authito: " + authInfo
			session['rtoken'] = authInfo.requestToken
			println "rtoken: " + authInfo.requestToken
			println "rtoken session: " + session['rtoken']
			
			session['authurl'] = authInfo.authUrl
			println "authurl: " + session['authurl']
			
//	        session["${params.provider}_authInfo"] = [authUrl : authInfo.authUrl, requestToken: authInfo.requestToken]
			session["${params.provider}_authInfo"] = authInfo
			println "authitoSession: " + session["${params.provider}_authInfo"]
			session["basofia"] = "mi string"
			println "basofia " + session["basofia"] 
			
	        redirect(url: authInfo.authUrl)
		}
    }

    def callback = {
        GrailsOAuthService service = resolveService(params.provider)
        if (!service) {
            redirect(url: '/')
        }

		println "pepe"
        AuthInfo authInfo = session["${params.provider}_authInfo"]

		println "basofia " + session["basofia"]
		println "pipo: ${authInfo}"
        def requestToken = authInfo.requestToken
		println "popo"
        def accessToken = service.getAccessToken(authInfo.service, params, requestToken)
        session["${params.provider}_authToken"] = accessToken

        def profile = service.getProfile(authInfo.service, accessToken)
        session["${params.provider}_profile"] = profile

        def uid = profile.uid
        User user = User.findByOauthIdAndOauthProvider(uid, params.provider)

        if (user) {
            springSecuritySigninService.signIn(user)
            redirect (controller:'home', action:'announcements')
        } else {
            redirect(controller: 'registerUser', action: 'register', params: params)
        }
    }

    private def resolveService(provider) {
        def serviceName = "${ provider as String }AuthService"
        grailsApplication.mainContext.getBean(serviceName)
    }

}
