package braid.domain

import grails.test.mixin.support.*
import braid.Announcement
import braid.User
import braid.course.Course

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Announcement)
class AnnouncementTests {

	void testHonorsConstraints() {
		
		def announcement = new Announcement()
		mockForConstraintsTests(Announcement, [announcement])
		
		assert !announcement.validate()
		assert 'nullable' == announcement.errors['text']
		assert 'nullable' == announcement.errors['announcer']
		assert 'nullable' == announcement.errors['course']
		
		announcement = new Announcement(
			text: 'some announcement',
			announcer: new User(),
			course: new Course())
		assert announcement.validate()
	}

}
