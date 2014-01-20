package braid.domain.homework

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.homework.Homework;

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class HomeworkSpec extends Specification {

	def homework

	def now
	def yesterday
	def tomorrow

	void setup() {
		now = new Date()
		yesterday = now - 1
		tomorrow = now + 1

		homework = new Homework()
		homework.dateService = [currentTime: now]
	}

	void "when dueDate after now then is not outOfDate"() {

		when:
			homework.dueDate = tomorrow
		then:
			!homework.outOfDate
	}

	void "when dueDate before now then is outOfDate"() {

		when:
			homework.dueDate = yesterday
		then:
			homework.outOfDate
	}

	void "when startDate after now then is not published"() {

		when:
			homework.startDate = tomorrow
		then:
			!homework.published
	}

	void "when startDate before now then is published"() {

		when:
			homework.startDate = yesterday
		then:
			homework.published
	}
}