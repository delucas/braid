package braid.domain.course

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.course.Option
import braid.course.Settings

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class SettingsSpec extends Specification {
	
	void "settings knows options set"() {
		given:
			def settings = new Settings()
			settings.options = Option.homework + Option.assignment
		expect:
			settings.has(Option.homework)
			settings.has(Option.assignment)
			!settings.has(Option.syllabus)
	}

}