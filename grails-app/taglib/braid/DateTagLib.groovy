package braid

class DateTagLib {

	def dateService
	def userService

	static namespace = 'braid'
	
	def dateInZone = { attrs ->
		
		Date date = attrs.date
		TimeZone tz = userService.currentTimeZone
		
		out << g.formatDate(date: date, timeZone: tz)
	}
}
