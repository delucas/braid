package braid.presenters.assignment

import groovy.time.TimeCategory


class AssignmentPresenter {

	def assignment
	def course
	def now
	def solutions
	def command
	def padawan

	def getBestSolution() {
		solutions.max { it.score }
	}

	boolean isPublished() {
		assignment.published
	}

	boolean canSubmit() {

		def result = true

		if (solutions) {
			result = !ungradedSolutions() || firstSolutionOldEnough()
		}

		padawan && result 
	}

	private boolean firstSolutionOldEnough() {
		use(TimeCategory) {
			solutions.first().dateCreated < now - 30.minutes
		}
	}

	private ungradedSolutions() {
		solutions.findAll { !it.graded }
	}
}
