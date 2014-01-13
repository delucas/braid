package braid.presenters.assignment

import braid.Assignment

class AssignmentListPresenter {

	def currentCourse
	def currentUser
	def currentTime

	def assignmentList

	def getAssignmentList() {
		assignmentList = assignmentList ?: gatherAssignmentList()
	}

	private gatherAssignmentList() {
		if (currentUser.hasRole('ROLE_JEDI')) {
			Assignment.byCourse(currentCourse).list()
		} else {
			Assignment.alreadyPublished(currentTime).byCourse(currentCourse).list()
		}
	}
}
