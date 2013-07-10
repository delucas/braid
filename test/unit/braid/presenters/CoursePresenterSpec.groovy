package braid.presenters
import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import groovy.time.TimeCategory
import spock.lang.Specification
import braid.AssignmentSolution
import braid.course.Option;

@TestMixin(GrailsUnitTestMixin)
class CoursePresenterSpec extends Specification {
	
	def presenter, course

	def setup() {
		course = [name: 'Estructura de Datos 1, primavera 2013', university: 'UNTreF']
		presenter = new CoursePresenter(course: course)
	}

	void "gets banner"() {
		expect: 'banner says course university and name'
			'UNTreF - Estructura de Datos 1, primavera 2013' == presenter.banner
	}
	
	void "gets no options"() {
		given: 'a course with no options'
			course << [options: 0]
		expect: 'empty options'
			0 == presenter.options.size()
	}
	
	void "gets an option"() {
		given: 'a course with one option'
			course << [options: 1]
		expect: 'one option'
			1 == presenter.options.size()
			presenter.options.contains(Option.syllabus)
	}
	
	void "gets two options"() {
		given: 'a course with two options'
			course << [options: 3]
		expect: 'two option'
			2 == presenter.options.size()
			presenter.options.contains(Option.syllabus)
			presenter.options.contains(Option.homework)
	}
	
	void "gets banner color"() {
		given: 'a course with banner color'
			course << [settings: [bannerColor: '#036']]
		expect: 'the color'
			'#036' == presenter.bannerColor
	}
	
	void "gets githubUsername"() {
		given: 'a course with github username'
			course << [settings: [githubUsername: 'lenguaje']]
		expect: 'the github username'
			'lenguaje' == presenter.githubUsername
	}
	
}