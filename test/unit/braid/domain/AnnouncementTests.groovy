package braid.domain

import static org.junit.Assert.*
import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.*

import braid.Announcement
import braid.Course
import braid.User

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Announcement)
class AnnouncementTests {

	void testHonorsConstraints() {
		
		def announcement = new Announcement()
		mockForConstraintsTests(Announcement, [announcement])
		
		assert !announcement.validate()
		def p = announcement.errors['text']
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
