package braid.domain

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.github.Repository

@TestMixin(GrailsUnitTestMixin)
class RepositorySpec extends Specification {
	
	def repo = new Repository(user: 'username', name: 'reponame')

	void "can get repo url"() {
		expect: 'repo url'
			'https://github.com/username/reponame' == repo.url
	}
	
	void "can get git repo url"() {
		expect: 'repo git url'
			'git@github.com:username/reponame.git' == repo.gitUrl
	}
	
	void "can get readme url"() {
		expect: 'repo raw README.md url'
			'https://raw.github.com/username/reponame/master/README.md' == repo.readmeUrl
	}
	
}