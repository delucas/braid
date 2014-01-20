package braid.domain.reviews

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.reviews.CodeReviewHomework

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class CodeReviewHomeworkSpec extends Specification {

	def homework

	def now
	def yesterday
	def tomorrow

	void setup() {
		now = new Date()
		yesterday = now - 1
		tomorrow = now + 1

		homework = new CodeReviewHomework()
		homework.dateService = [currentTime: now]
	}

	void "when reviewDueDate after now then is not outOfDate"() {

		when:
			homework.reviewDueDate = tomorrow
		then:
			!homework.outOfDate
	}

	void "when reviewDueDate before now then is outOfDate"() {

		when:
			homework.reviewDueDate = yesterday
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

	void "before solutionDueDate nextDueDate is solutionDueDate"() {

		when:
			homework.solutionDueDate = tomorrow
		then:
			tomorrow == homework.nextDueDate
	}

	void "after solutionDueDate nextDueDate is reviewDueDate"() {

		when:
			homework.solutionDueDate = yesterday
			homework.reviewDueDate = tomorrow
		then:
			tomorrow == homework.nextDueDate
	}

	void "before solutionDueDate stage is 'solve'"() {

		when:
			homework.solutionDueDate = tomorrow
		then:
			'solve' == homework.stage
	}

	void "after solutionDueDate stage is 'review'"() {

		when:
			homework.solutionDueDate = yesterday
			homework.reviewDueDate = tomorrow
		then:
			'review' == homework.stage
	}

	void "after reviewDueDate stage is 'results'"() {

		when:
			homework.solutionDueDate = yesterday - 1
			homework.reviewDueDate = yesterday
		then:
			'results' == homework.stage
	}
}