package braid.presenters

import grails.test.mixin.*
import grails.test.mixin.support.*
import braid.Homework

@TestMixin(GrailsUnitTestMixin)
class PadawanHomeworkPresenterTests {
	
	void testWhenCanSolveAndPadawan() {
		def presenter = new PadawanHomeworkPresenter(padawan: true, homework: new HomeworkStub(outOfDate: false))

		assert presenter.canSolve()
	}
	
	void testWhenCannotSolveAndPadawan() {
		def presenter = new PadawanHomeworkPresenter(padawan: true, homework: new HomeworkStub(outOfDate: true))

		assert !presenter.canSolve()
	}
	
	void testWhenCanSolveAndNotPadawan() {
		def presenter = new PadawanHomeworkPresenter(padawan: false, homework: new HomeworkStub(outOfDate: false))

		assert !presenter.canSolve()
	}
	
	void testWhenCannotSolveAndNotPadawan() {
		def presenter = new PadawanHomeworkPresenter(padawan: false, homework: new HomeworkStub(outOfDate: true))

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