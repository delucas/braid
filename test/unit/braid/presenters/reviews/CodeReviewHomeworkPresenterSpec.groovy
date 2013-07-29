package braid.presenters.reviews
import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.reviews.CodeReviewHomework

@TestMixin(GrailsUnitTestMixin)
class CodeReviewHomeworkPresenterSpec extends Specification {
	
	def now = new Date()
	def presenter = new CodeReviewHomeworkPresenter()
	def homework = new CodeReviewHomework()
	
	void setup() {
		presenter.now = now
		presenter.homework = homework
	}
	
	void 'homework in solvable stage'() {
		given: 'homework with solutionDueDate tomorrow'
			homework.solutionDueDate = new Date() + 1
		expect: 'homework in solvable stage'
			presenter.stage == 'solve'
	}

	void 'homework in results stage'() {
		given: 'homework with reviewDueDate yesterdar'
			homework.reviewDueDate = new Date() - 1
		expect: 'homework in results stage'
			presenter.stage == 'results'
	}

	void 'homework in review stage'() {
		given: 'homework with reviewDueDate tomorrow and solutionDueDate yesterday'
			homework.solutionDueDate = new Date() - 1
			homework.reviewDueDate = new Date() + 1
		expect: 'homework in review stage'
			presenter.stage == 'review'
	}

}