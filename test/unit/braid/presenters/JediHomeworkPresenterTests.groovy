package braid.presenters

import grails.test.mixin.*
import grails.test.mixin.support.*
import braid.homework.Homework
import braid.presenters.homework.JediHomeworkPresenter

@TestMixin(GrailsUnitTestMixin)
class JediHomeworkPresenterTests {

	void testDuckTypes() {
		def ducks = [
			new HomeworkStub(),
			new Homework()
		]

		ducks.each { it ->
			assert it.metaClass.hasProperty(it, 'outOfDate')
			assert it.metaClass.hasProperty(it, 'published')
		}
	}

	void testWhenHasToGradeAndInTime() {
		def presenter = new JediHomeworkPresenter(totalToGrade: 1, homework: new HomeworkStub(outOfDate: true))

		assert presenter.hasToGrade()
		assert !presenter.finishedGrading()
	}

	void testWhenHasNotToGradeAndInTime() {
		def presenter = new JediHomeworkPresenter(totalToGrade: 0, homework: new HomeworkStub(outOfDate: true))

		assert !presenter.hasToGrade()
		assert presenter.finishedGrading()
	}

	void testWhenHasToGradeAndNotInTime() {
		def presenter = new JediHomeworkPresenter(totalToGrade: 1, homework: new HomeworkStub(outOfDate: false))

		assert !presenter.hasToGrade()
		assert !presenter.finishedGrading()
	}

	void testWhenHasNotToGradeAndNotInTime() {
		def presenter = new JediHomeworkPresenter(totalToGrade: 0, homework: new HomeworkStub(outOfDate: false) )

		assert !presenter.hasToGrade()
		assert !presenter.finishedGrading()
	}

	void testWhenPublished() {
		def presenter = new JediHomeworkPresenter(homework: new HomeworkStub(published: true) )

		assert presenter.published
	}

	void testWhenNotPublished() {
		def presenter = new JediHomeworkPresenter(homework: new HomeworkStub(published: false) )

		assert !presenter.published
	}
}

public class HomeworkStub {
	def outOfDate
	def published
}
