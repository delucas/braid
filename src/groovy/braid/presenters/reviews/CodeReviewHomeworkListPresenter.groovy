package braid.presenters.reviews

import braid.reviews.CodeReviewHomework

class CodeReviewHomeworkListPresenter {

	def currentCourse
	def currentUser
	def currentTime

	def homeworkList

	def getHomeworkList() {
		homeworkList = homeworkList ?: gatherHomeworkList()
	}
	
	private gatherHomeworkList() {
		if (currentUser.hasRole('ROLE_JEDI')) {
			CodeReviewHomework.byCourse(currentCourse).list()
		} else {
			CodeReviewHomework.alreadyPublished(currentTime).byCourse(currentCourse).list()
		}
	}
	
}
