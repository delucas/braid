package braid.presenters
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import groovy.time.TimeCategory
import spock.lang.Specification
import braid.assignment.AssignmentSolution
import braid.presenters.assignment.AssignmentPresenter

@TestMixin(GrailsUnitTestMixin)
class AssignmentPresenterSpec extends Specification {

	def now = new Date()
	def padawanPresenter, notPadawanPresenter

	def setup() {
		padawanPresenter = new AssignmentPresenter(now: now, padawan: true)
		notPadawanPresenter = new AssignmentPresenter(now: now, padawan: false)
	}

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

	void "when not padawan then is not able to submit"() {
		expect: 'can not submit'
			!notPadawanPresenter.canSubmit()
	}

	void "when no solution then is able to resubmit"() {
		given: 'empty solutions'
			padawanPresenter.solutions = []
		expect: 'can submit'
			padawanPresenter.canSubmit()
	}

	void "when solutions null then is able to resubmit"() {
		given: 'no solutions attr'
			padawanPresenter.solutions = null
		expect: 'can submit'
			padawanPresenter.canSubmit()
	}

	void "when all solutions are graded is able to resubmit"() {
		given: 'a solutions list with some elements, all graded'
			def solutionOne = new AssignmentSolutionStub(graded: true)
			def solutionTwo = new AssignmentSolutionStub(graded: true)
			padawanPresenter.solutions = [solutionOne, solutionTwo]
		expect: 'can submit'
			padawanPresenter.canSubmit()
	}

	void "when all solutions are not graded and are new is not able to resubmit"() {
		given: 'a solutions list with some elements, some new not graded'
			def solutionOne, solutionTwo
			use(TimeCategory) {
				solutionOne = new AssignmentSolutionStub(graded: false, dateCreated: now - 29.minutes)
				solutionTwo = new AssignmentSolutionStub(graded: true, dateCreated: now - 54.minutes)
			}
			padawanPresenter.solutions = [solutionOne, solutionTwo]
		expect: 'can not submit'
			!padawanPresenter.canSubmit()
	}

	void "when all solutions are not graded and are old is able to resubmit"() {
		given: 'a solutions list with some elements, one ungraded'
			def solutionOne, solutionTwo
			use(TimeCategory) {
				solutionOne = new AssignmentSolutionStub(graded: false, dateCreated: now - 31.minutes)
				solutionTwo = new AssignmentSolutionStub(graded: true, dateCreated: now - 1.hour)
			}
			padawanPresenter.solutions = [solutionOne, solutionTwo]
		expect: 'can submit'
			padawanPresenter.canSubmit()
	}

	void "when published"() {
		given: 'a published assignment'
			def presenter = new AssignmentPresenter(assignment: new AssignmentStub(published: true))
		expect: 'is published'
			presenter.published
	}

	void "when not published"() {
		given: 'an unpublished assignment'
			def presenter = new AssignmentPresenter(assignment: new AssignmentStub(published: false))
		expect: 'is not published'
			!presenter.published
	}

	void "test duck types"() {
		expect: 'duck responds to methods'
			duck.metaClass.hasProperty(duck, 'graded')
			duck.metaClass.hasProperty(duck, 'dateCreated')
		where:
			duck << [new AssignmentSolution(), new AssignmentSolutionStub()]
	}
}

public class AssignmentSolutionStub {
	def graded
	def dateCreated
}

public class AssignmentStub {
	def published
}