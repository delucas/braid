package braid.domain.assignment

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.assignment.AssignmentSolution

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class AssignmentSolutionSpec extends Specification {

	def assignmentSolution = new AssignmentSolution()
	
	void "when no feedback then is not graded"() {
		
		given: 'an assignmentSolution without feedback'
		expect: 'assignmentSolution is not graded'
			!assignmentSolution.graded
	}
	
	void "when feedback then is graded"() {
		
		given: 'an assignmentSolution with feedback'
			assignmentSolution.feedback = 'Some feedback'
		expect: 'assignmentSolution is graded'
			assignmentSolution.graded
	}
}