package braid

import groovy.time.TimeCategory

class DateService {

	def userService

	def getCurrentTime() {
		new Date()
	}
	
	def getCurrentTimeInZone() {
		toLocal(currentTime, userService.currentTimeZone)
	}

	def toUTC(Date dateInZone, TimeZone tz) {
		TimeZone utcTimeZone = TimeZone.getTimeZone('UTC')

		long oldDateinMilliSeconds = dateInZone.time - tz.rawOffset
		Date dateInGMT = new Date(oldDateinMilliSeconds)
		long convertedDateInMilliSeconds = dateInGMT.time + utcTimeZone.rawOffset
		
		new Date(convertedDateInMilliSeconds)
	}

	def toLocal(Date dateInUTC, TimeZone tz) {
		long oldDateinMilliSeconds = dateInUTC.time
		Date dateInZone = new Date(oldDateinMilliSeconds)
		long convertedDateInMilliSeconds = dateInZone.time + tz.rawOffset

		new Date(convertedDateInMilliSeconds)
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
