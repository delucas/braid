package braid.presenters

import braid.Homework

class JediHomeworkPresenter {

	Homework homework
	def totalToGrade
	def solutionsUpToDate
	def now

	boolean hasToGrade() {
		totalToGrade > 0
	}
	
	boolean finishedGrading() {
		isTimeToGrade() && totalToGrade == 0
	}
	
	private boolean isTimeToGrade() {
		homework.dueDate < now
	}
	
}
