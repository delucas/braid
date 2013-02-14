package braid

class AnnouncementTagLib {

	def dateService
	
	static namespace = 'announcement'
	
	def upcomingDate = { attrs ->
		
		def event = attrs.event
		
		out << '<div class="well">'
		out << '	<div>'
		out << '		<strong>'
		out << g.link(controller: event.type, action: 'show', id: event.id) { event.title }
		out << '		</strong>'
		out << '	</div>'
		out << '	<div>'
		out << '		<time>' + g.formatDate(date: event.dueDate, timeZone: "America/Argentina/Buenos_Aires") + '</time>'
		out << '	</div>'
		out << '	<div>'
		out << '		<small>(' + g.message(code: "dates.timeTo.${dateService.timeTo(event.dueDate)}") + ')</small>'
		out << '	</div>'
		out << '</div>'
	}
	
}
