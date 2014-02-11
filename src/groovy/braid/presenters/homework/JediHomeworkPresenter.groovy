package braid.presenters.homework

class JediHomeworkPresenter {

	def homework
	def totalToGrade
	def solutionsUpToDate
	def now

	boolean hasToGrade() {
		homework.outOfDate && totalToGrade > 0
	}
	
	boolean finishedGrading() {
		homework.outOfDate && totalToGrade == 0
	}
	
	boolean isPublished() {
		homework.published
	}

}
