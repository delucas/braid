package braid.presenters

import grails.test.mixin.*
import grails.test.mixin.support.*
import braid.Homework

@TestMixin(GrailsUnitTestMixin)
class PadawanHomeworkPresenterTests {
	
	void testWhenCanSolve() {
		def presenter = new PadawanHomeworkPresenter(homework: new HomeworkStub(outOfDate: false))

		assert presenter.canSolve()
	}
	
	void testWhenCannotSolve() {
		def presenter = new PadawanHomeworkPresenter(homework: new HomeworkStub(outOfDate: true))

		assert !presenter.canSolve()
	}
	
	void testWhenSolvedAndCanSolve() {
		def presenter = new PadawanHomeworkPresenter(alreadySolved: true, homework: new HomeworkStub(outOfDate: false))

		assert presenter.shouldAlertForAlreadySolved()
	}
	
	void testWhenNotSolvedAndCanSolve() {
		def presenter = new PadawanHomeworkPresenter(alreadySolved: false, homework: new HomeworkStub(outOfDate: false))

		assert !presenter.shouldAlertForAlreadySolved()
	}
	
	void testWhenSolvedAndCannotSolve() {
		def presenter = new PadawanHomeworkPresenter(alreadySolved: true, homework: new HomeworkStub(outOfDate: true))

		assert !presenter.shouldAlertForAlreadySolved()
	}
	
	void testWhenNotSolvedAndCannotSolve() {
		def presenter = new PadawanHomeworkPresenter(alreadySolved: false, homework: new HomeworkStub(outOfDate: true))

		assert !presenter.shouldAlertForAlreadySolved()
	}
	
	

}