package braid.presenters.homework

import braid.Homework

class HomeworkListPresenter {

	def currentCourse
	def currentUser
	def currentTime

	def homeworkList

	def getHomeworkList() {
		homeworkList = homeworkList ?: gatherHomeworkList()
	}
	
	private gatherHomeworkList() {
		if (currentUser.hasRole('ROLE_JEDI')) {
			Homework.byCourse(currentCourse).list()
		} else {
			Homework.alreadyPublished(currentTime).byCourse(currentCourse).list()
		}
	}
	
}
