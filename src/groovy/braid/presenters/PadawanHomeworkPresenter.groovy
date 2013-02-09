package braid.presenters

import braid.Homework

class PadawanHomeworkPresenter {
	
	def homework
	def course
	def homeworkSolution
	def alreadySolved
	def now
	
	boolean shouldAlertForAlreadySolved() {
		alreadySolved && !homework.outOfDate
	}
	
	boolean canSolve() {
		!homework.outOfDate
	}
	
}
