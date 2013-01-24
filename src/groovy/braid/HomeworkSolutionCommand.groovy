package braid

import grails.validation.Validateable;

@Validateable
class HomeworkSolutionCommand {

	Long homeworkSolutionId
	String text
	Boolean honorCode
	
	static constraints = {
		homeworkSolutionId nullable: true
		text blank: false, nullable: false
		honorCode validator: { val -> val==true }
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
	
	static def createFrom(HomeworkSolution origin) {
		if (origin) new HomeworkSolutionCommand(text: origin.text, homeworkSolutionId: origin.id)
		else new HomeworkSolutionCommand()
	}
	
}
