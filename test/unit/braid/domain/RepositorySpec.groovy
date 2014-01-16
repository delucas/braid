package braid.domain

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.github.Repository

@TestMixin(GrailsUnitTestMixin)
class RepositorySpec extends Specification {
	
	void "can get repo url"() {
		given: 'some repo'
			def repo = new Repository(user: 'tallerweb', name: 'ecuaciones')
		expect: 'repo url'
			'https://github.com/tallerweb/ecuaciones' == repo.url
	}
	
	void "can get git repo url"() {
		given: 'some repo'
			def repo = new Repository(user: 'username', name: 'reponame')
		expect: 'repo git url'
			'git@github.com:username/reponame.git' == repo.gitUrl
	}
	
	void "can get readme url"() {
		given: 'some repo'
			def repo = new Repository(user: 'tallerweb', name: 'ecuaciones')
		expect: 'repo raw README.md url'
			'https://raw.github.com/tallerweb/ecuaciones/master/README.md' == repo.readmeUrl
	}
	
}