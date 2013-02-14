package braid.domain

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.Assignment

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class AssignmentSpec extends Specification {

	def assignment = new Assignment()
	def now = new Date()
	
	def setup () {
		assignment.dateService = [currentTime: now]
	}
		
	void "when dueDate after now then is not outOfDate"() {
		
		given: 'an assignment with tomorrow as dueDate'
			def tomorrow = now + 1
			assignment.dueDate = tomorrow
		expect: 'assignment not out of date'
			!assignment.outOfDate
	}
	
	void "when dueDate before now then is outOfDate"() {
		
		given: 'an assignment with yesterday as dueDate'
			def yesterday = now - 1
			assignment.dueDate = yesterday
		expect: 'assignment out of date'
			assignment.outOfDate
	}
}