package braid

import braid.presenters.AnnouncementPresenter

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
		out << '		<time>' + braid.dateInZone(date: event.dueDate) + '</time>'
		out << '	</div>'
		out << '	<div>'
		out << '		<small>(' + g.message(code: "dates.timeTo.${dateService.timeTo(event.dueDate)}") + ')</small>'
		out << '	</div>'
		out << '</div>'
	}
	
	def render = { attrs ->
		Announcement announcement = attrs.data
		Date now = dateService.currentTime

		AnnouncementPresenter presenter = new AnnouncementPresenter(announcement: announcement, now: now)

		out << '<blockquote class="md">'

		if (presenter.isNew()) {
			out << '	<span class="label label-important pull-right">Nuevo</span>'
		}

		out << '	' + announcement.text
		out << '	<small>'
		out << "		${announcement.announcer.name}"
		out << "		<span class='pull-right'>${braid.dateInZone(date:announcement.dateCreated)}</span>"
		out << '	</small>'
		out << '</blockquote>'
	}

}
