package braid.presenters.homework

class PadawanHomeworkPresenter {
	
	def homework
	def course
	def homeworkSolution
	def alreadySolved
	def now
	def padawan
	
	boolean shouldAlertForAlreadySolved() {
		alreadySolved && !homework.outOfDate
	}
	
	boolean canSolve() {
		!homework.outOfDate && padawan
	}

	boolean isJarJar() {
		!padawan
	}
	
}
