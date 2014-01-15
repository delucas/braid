package braid

import groovy.time.TimeCategory
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
		out << '		<time>' + g.formatDate(date: event.dueDate, timeZone: "America/Argentina/Buenos_Aires") + '</time>'
		out << '	</div>'
		out << '	<div>'
		out << '		<small>(' + g.message(code: "dates.timeTo.${dateService.timeTo(event.dueDate)}") + ')</small>'
		out << '	</div>'
		out << '</div>'
	}
	
	def render = { attrs ->
		Announcement announcement = attrs.data
		Date now = new Date()

		AnnouncementPresenter presenter = new AnnouncementPresenter(announcement: announcement, now: now)

		out << '<blockquote class="md">'

		if (presenter.isNew()) {
			out << '	<span class="label label-important pull-right">Nuevo</span>'
		}

		out << '	' + announcement.text
		out << '	<small>'
		out << "		${announcement.announcer.name}"
		out << "		<span class='pull-right'>${g.formatDate(date:announcement.dateCreated, timeZone:'America/Argentina/Buenos_Aires')}</span>"
		out << '	</small>'
		out << '</blockquote>'
	}

}
