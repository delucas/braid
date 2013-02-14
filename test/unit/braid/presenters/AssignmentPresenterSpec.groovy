package braid.presenters

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.AssignmentSolution

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class AssignmentPresenterSpec extends Specification {
	
	void "gets bestSolution from trivial solutions list"() {
		
		given: 'a solutions list with one element'
			def solutionOne = new AssignmentSolution(score: 0.0)
			def presenter = new AssignmentPresenter(solutions: [solutionOne])
		expect: 'bestSolution is the only solution'
			solutionOne == presenter.bestSolution
	}
	
	void "gets bestSolution from solutions list"() {
		
		given: 'a solutions list with some elements'
			def solutionOne = new AssignmentSolution(score: 0.0)
			def solutionTwo = new AssignmentSolution(score: 1.0)
			def presenter = new AssignmentPresenter(solutions: [solutionOne, solutionTwo])
		expect: 'bestSolution is the solution with max score'
			solutionTwo == presenter.bestSolution
	}

	void "when no solution then is able to resubmit"() {
		
		given: 'an empty solutions'
			def presenter = new AssignmentPresenter(solutions: [])
		expect: 'can submit'
			presenter.canSubmit()
	}
		
	void "when all solutions are graded is able to resubmit"() {
		
		given: 'a solutions list with some elements, all graded'
			def solutionOne = new AssignmentSolutionStub(graded: true)
			def solutionTwo = new AssignmentSolutionStub(graded: true)
			def presenter = new AssignmentPresenter(solutions: [solutionOne, solutionTwo])
		expect: 'can submit'
			presenter.canSubmit()
	}
	
	void "when a solution is not graded then is not able to resubmit"() {
		
		given: 'a solutions list with some elements, one ungraded'
			def solutionOne = new AssignmentSolutionStub(graded: true)
			def solutionTwo = new AssignmentSolutionStub(graded: false)
			def presenter = new AssignmentPresenter(solutions: [solutionOne, solutionTwo])
		expect: 'can not submit'
			!presenter.canSubmit()
	}
	
	void "test duck types"() {
		expect: 'duck responds to graded'
			duck.metaClass.hasProperty(duck, 'graded')
		where:
			duck << [new AssignmentSolution(), new AssignmentSolutionStub()]
	}

}

public class AssignmentSolutionStub {
	def graded
}