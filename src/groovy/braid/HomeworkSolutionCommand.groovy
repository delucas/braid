package braid

import grails.validation.Validateable
import braid.homework.HomeworkSolution

@Validateable
class HomeworkSolutionCommand {

	Long homeworkSolutionId
	String text
	Boolean honorCode
	Object feedback = null

	static constraints = {
		homeworkSolutionId nullable: true
		text blank: false, nullable: false
		honorCode validator: { val -> val == true }
	}


	// Para asegurar el duck typing
	def getId() {
		homeworkSolutionId?:null
	}

	def toHomeworkSolution() {
		def result
		if (homeworkSolutionId) {
			result = HomeworkSolution.get(homeworkSolutionId)
		} else {
			result = new HomeworkSolution()
		}
		result.text = text
		result
	}
}
