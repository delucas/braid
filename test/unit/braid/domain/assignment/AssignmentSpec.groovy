package braid.domain.assignment

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.assignment.Assignment

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class AssignmentSpec extends Specification {

	def assignment = new Assignment()

	def now
	def yesterday
	def tomorrow

	def setup () {
		now = new Date()
		yesterday = now - 1
		tomorrow = now + 1

		assignment.dateService = [currentTime: now]
	}

	void "when dueDate after now then is not outOfDate"() {

		given: 'an assignment with tomorrow as dueDate'
			assignment.dueDate = tomorrow
		expect: 'assignment not out of date'
			!assignment.outOfDate
	}

	void "when dueDate before now then is outOfDate"() {

		given: 'an assignment with yesterday as dueDate'
			assignment.dueDate = yesterday
		expect: 'assignment out of date'
			assignment.outOfDate
	}

	void "when startDate after now then is not published"() {

		when:
			assignment.startDate = tomorrow
		then:
			!assignment.published
	}

	void "when startDate before now then is published"() {

		when:
			assignment.startDate = yesterday
		then:
			assignment.published
	}
}