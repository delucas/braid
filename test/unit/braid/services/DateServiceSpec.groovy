package braid.services

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import groovy.time.TimeCategory
import spock.lang.Specification
import braid.DateService

@TestMixin(GrailsUnitTestMixin)
class DateServiceSpec extends Specification {

	def dateService = new DateService()

	void "timeTo when date before now"() {
		given: 'now and a date before now'
			def now = dateService.currentTime
			def yesterday = now - 1
		expect: 'time is up'
			'timeIsUp' == dateService.timeTo(yesterday)
	}

	void "timeTo when less than an hour to go"() {
		given: 'now and a date in the next minutes'
			def now = dateService.currentTime
			def inSomeMinutes
			use(TimeCategory) {
				inSomeMinutes = now + 33.minutes
			}
		expect: 'some minutes left'
			'someMinutesLeft' == dateService.timeTo(inSomeMinutes)
	}

	void "timeTo when less then a day to go"() {
		given: 'now and a date in the next hours'
			def now = dateService.currentTime
			def inSomeHours
			use(TimeCategory) {
				inSomeHours = now + 14.hours
			}
		expect: 'some hours'
			'someHoursLeft' == dateService.timeTo(inSomeHours)
	}

	void "timeTo when less then a week to go"() {
		given: 'now and a date in the next days'
			def now = dateService.currentTime
			def inSomeDays
			use(TimeCategory) {
				inSomeDays = now + 3.days
			}
		expect: 'some days'
			'someDaysLeft' == dateService.timeTo(inSomeDays)
	}

	void "timeTo when less then a month to go"() {
		given: 'now and a date in the next weeks'
			def now = dateService.currentTime
			def inSomeWeeks
			use(TimeCategory) {
				inSomeWeeks = now + 2.weeks
			}
		expect: 'some weeks'
			'someWeeksLeft' == dateService.timeTo(inSomeWeeks)
	}
	
	void "timeTo when more then a month to go"() {
		given: 'now and a date in the next month'
			def now = dateService.currentTime
			def inTheNextMonth
			use(TimeCategory) {
				inTheNextMonth = now + 1.month + 3.days
			}
		expect: 'more than a month'
			'moreThanAMonthLeft' == dateService.timeTo(inTheNextMonth)
	}
}