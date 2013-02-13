package braid.domain

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.Homework

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class HomeworkSpec extends Specification {

	def homework
	def now
	
	void setup() {
		now = new Date()
		
		homework = new Homework()
		homework.dateService = [currentTime: now]
	}
	
	void "when dueDate after now then is not outOfDate"() {
		
		given:
			def tomorrow = now + 1
		when:
			homework.dueDate = tomorrow
		then:
			!homework.outOfDate
	}
	
	void "when dueDate before now then is outOfDate"() {
		
		given:
			def yesterday = now - 1
		when:
			homework.dueDate = yesterday
		then:
			homework.outOfDate
	}
}