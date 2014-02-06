package braid.presenters.course
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.Link
import braid.course.Option

@TestMixin(GrailsUnitTestMixin)
@Mock(Link)
class CoursePresenterSpec extends Specification {

	def presenter, course

	def setup() {
		course = [name: 'Estructura de Datos 1, primavera 2013', university: 'UNTreF', settings: [:]]
		presenter = new CoursePresenter(course: course)
	}

	void "gets course name"() {
		expect: 'the course name'
			'Estructura de Datos 1, primavera 2013' == presenter.name
	}

	void "gets university name"() {
		expect: 'the university name'
			'UNTreF' == presenter.university
	}

	void "knows if has the option"() {
		given: 'a course with one option'
			course << [settings: [ has: { option -> true } ]]
		expect: 'hast the option'
			presenter.hasOption(Option.assignment)
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

	void "when there is no links it returns no links"() {
		given: 'a course with no links'
			Link.metaClass.'static'.byCourse = { course -> [list: { [] } ] }
		expect: 'returns no links'
			!presenter.links
	}

	void "when there are links it returns the links"() {
		given: 'a course with no links'
			Link.metaClass.'static'.byCourse = { course -> [list: { [new Link()] } ] }
		expect: 'returns no links'
			presenter.links
	}

}