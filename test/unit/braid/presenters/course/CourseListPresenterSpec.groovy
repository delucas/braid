package braid.presenters.course
import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.course.Course

@TestMixin(GrailsUnitTestMixin)
class CourseListPresenterSpec extends Specification {


	void "if no courses gets empty list"() {
		given: 'a presenter with no courses'
			def presenter = new CourseListPresenter(courses: [])
		expect: 'an empty list'
			[] == presenter.courses
	}
	
	void "if some courses gets courses list"() {
		given: 'a presenter with two courses'
			def presenter = new CourseListPresenter(courses: [new Course(), new Course()])
		expect: 'a list with two entries'
			2 == presenter.courses.size()
	}
	
	void "admin role can admin courses"() {
		given: 'a presenter for an admin'
			def presenter = new CourseListPresenter(user: [hasRole: { true } ])
		expect: 'admin can admin courses'
			presenter.canAdmin()
			presenter.canCreate()
	}
	
	void "other roles cannot admin courses"() {
		given: 'a presenter for no admin role'
			def presenter = new CourseListPresenter(user: [hasRole: { false } ])
		expect: 'no admin cannot admin courses'
			!presenter.canAdmin()
			!presenter.canCreate()
	}
	
	void "admin role cannot join course"() {
		given: 'a presenter for an admin'
			def presenter = new CourseListPresenter(user: [hasRole: { true } ])
		expect: 'admin cannot join courses'
			!presenter.canJoin()
	}

	void "other roles can join course"() {
		given: 'a presenter for no admin role'
			def presenter = new CourseListPresenter(user: [hasRole: { false } ])
		expect: 'no admin can join courses'
			presenter.canJoin()
	}

}