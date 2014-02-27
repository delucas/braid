package braid.assignment

import grails.converters.JSON

class GraderComputationService {

	static transactional = true

	def grailsApplication
	def notificationService

	final static rabbitQueue = 'alreadyGradedQueue'

	void handleMessage(message) {
		def returnedMap = JSON.parse(new String(message))

		AssignmentSolution solution = AssignmentSolution.get(returnedMap.get('solutionId') as Long)

		solution.feedback = returnedMap.get('testDetail')
		solution.score = (returnedMap.get('testScore') as Double).trunc(2)

		solution.save(flush: true)

		notificationService.informGrade(solution)
	}
}
