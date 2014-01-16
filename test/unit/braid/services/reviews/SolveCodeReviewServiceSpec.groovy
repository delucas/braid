package braid.services.reviews

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import braid.User
import braid.reviews.CodeReviewHomework
import braid.reviews.CodeReviewSolution
import braid.reviews.CodeReviewSolutionCommand
import braid.reviews.SolveCodeReviewService

@TestMixin(GrailsUnitTestMixin)
@Mock(CodeReviewSolution)
class SolveCodeReviewServiceSpec extends Specification {

	def service = new SolveCodeReviewService()

	void "converts a solution command into a new solution"() {
		given: 'a valid command object and context'
			def command = new CodeReviewSolutionCommand(honorCode: true, gist: 1)
			def homework = new CodeReviewHomework()
			def user = new User()
		when: 'builds solution'
			CodeReviewSolution solution = service.buildSolution(command, homework, user)
		then: 'solution is valid'
			solution.validate()
	}

	void "updates a solution based on a command"() {
		given: 'a valid command object and context'
			def oldSolution = new CodeReviewSolution().with {
				homework = new CodeReviewHomework()
				gist = 123
				user = new User()
				it
			}
			oldSolution.save()

			def command = new CodeReviewSolutionCommand(solutionId: oldSolution.id, honorCode: true, gist: 456)

		when: 'builds solution'
			CodeReviewSolution solution = service.buildSolution(command, oldSolution.homework, oldSolution.user)
			solution.save()

		then: 'solution is the same as old solution'
			solution.id == oldSolution.id
			solution.gist == 456
	}
}
