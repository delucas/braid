package braid.presenters.reviews
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.User
import braid.reviews.CodeReview
import braid.reviews.CodeReviewHomework
import braid.reviews.CodeReviewSolution

@TestMixin(GrailsUnitTestMixin)
class CodeReviewResultsPresenterSpec extends Specification {
	
	def presenter = new CodeReviewResultsPresenter()
	def homework = new CodeReviewHomework()
	def user = new User(name: 'Someone')
	def solution = new CodeReviewSolution(user: user)
	
	void setup() {
		presenter.homework = homework
		presenter.solution = solution
	}
	
	void 'when there is own review it gets own review'() {
		given: 'a list of reviews with own solution'
			def ownReview = new CodeReview(user: user)
			presenter.reviews = [new CodeReview(), ownReview]
		expect: 'presenter knows how to retrieve own review'
			ownReview == presenter.ownReview
	}
	
	void 'when there is own review it gets all but own review'() {
		given: 'a list of reviews with own solution'
			def ownReview = new CodeReview(user: user)
			presenter.reviews = [new CodeReview(), ownReview]
		expect: 'presenter knows how to retrieve own review'
			!presenter.reviews.contains(ownReview)
			1 == presenter.reviews.size
	}
	
	void 'when there is not own review it does not get it'() {
		given: 'a list of reviews without own solution'
			presenter.reviews = [new CodeReview(), new CodeReview()]
		expect: 'presenter knows how to retrieve own review'
			null == presenter.ownReview
	}
	
	void 'when there is not own review it does not get it on reviews'() {
		given: 'a list of reviews without own solution'
			presenter.reviews = [new CodeReview(), new CodeReview()]
			def ownReview = new CodeReview(user: user)
		expect: 'presenter knows how to retrieve own review'
			!presenter.reviews.contains(ownReview)
			2 == presenter.reviews.size
	}

}