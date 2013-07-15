package oauth.mock

import braid.User

class GithubMockController {

	def springSecurityService
    def index() { 
		def users = User.findAll()
		[users:users]
	}
	
	def signinUser(Long id){
		
		def user = User.get(id)
		springSecurityService.reauthenticate (user.username)
		redirect (controller:'home', action:'index')
	}
	
	def register(){
		
		def username = 'usuario' + new Date().time.toString()
		def provider = 'github'
		session["${provider}_profile"] = [uid:UUID.randomUUID() as String,picture:'', username:username, email:username]
		session["${provider}_authToken"] = 'mockToken'
		
		
		def parameters = [user:[username:username,email: username, oauthProvider:provider], provider:provider]
		redirect (controller:'registerUser', action:'register', params:parameters)
	}
}
