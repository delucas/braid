package braid.presenters
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class AnnouncementPresenterSpec extends Specification {
	
	def now = new Date()

	void "announcement is new if has less than three days old"() {
		given: 'an announcement from yesterday'
			def announcement = [dateCreated: now - 1]
			def presenter = new AnnouncementPresenter(announcement: announcement, now: now)
		expect: 'announcement is new'
			presenter.isNew()
	}
	
	void "announcement is not new if has more than three days old"() {
		given: 'an announcement from last week'
			def announcement = [dateCreated: now - 7]
			def presenter = new AnnouncementPresenter(announcement: announcement, now: now)
		expect: 'announcement is not new'
			!presenter.isNew()
	}

}
