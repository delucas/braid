package braid.github

class Repository {

	String user
	String name
	
	static transients = ['readmeUrl', 'url', 'gitUrl']
	
	static mapping = {
		user column: '`user`'
	}
	
	def getUrl() {
		"https://github.com/${user}/${name}"
	}
	
	def getReadmeUrl() {
		"https://raw.github.com/${user}/${name}/master/README.md"
	}
	
	def getGitUrl() {
		"git@github.com:${user}/${name}.git"
	}

}