package braid.presenters

import groovy.time.TimeCategory


class AnnouncementPresenter {

	def announcement
	def now

	boolean isNew() {
		use(TimeCategory) {
			def threeDaysAgo = now - 3.days
			announcement.dateCreated.after(threeDaysAgo)
		}
	}
}
