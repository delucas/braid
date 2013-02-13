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

}