package braid

import groovy.time.TimeCategory
import groovy.time.TimeDuration

class DateService {

    def getCurrentTime() {
		new Date()
	}
	
	def timeTo(def date) {
		
		def duration = TimeCategory.minus(date, currentTime).toMilliseconds()
		
		if (duration < 0L) {
			'timeIsUp'
		} else if (duration < 60L * 60 * 1000) {
			'someMinutesLeft'
		} else if (duration < 24L * 60 * 60 * 1000) {
			'someHoursLeft'
		} else if (duration < 7L * 24 * 60 * 60 * 1000) {
			'someDaysLeft'
		} else if (duration < 30L * 24 * 60 * 60 * 1000) {
			'someWeeksLeft'
		} else {
			'moreThanAMonthLeft'
		}
	}
}
