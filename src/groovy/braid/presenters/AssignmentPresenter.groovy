package braid.presenters


class AssignmentPresenter {
	
	def assignment
	def course
	def now
	def solutions
	def command

	def getBestSolution(){
		solutions.max { it.score }
	}
	
	boolean canSubmit() {
		!solutions.findAll { !it.graded }
	}
		
}
