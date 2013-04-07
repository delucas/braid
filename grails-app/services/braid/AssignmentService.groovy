package braid

import grails.converters.JSON

class AssignmentService {

	static transactional = true
	def grailsApplication

	static rabbitQueue = 'alreadyGradedQueue'

	void handleMessage(message) {
		def returnedMap = JSON.parse(new String(message))

		AssignmentSolution solution = AssignmentSolution.get(returnedMap.get('solutionId') as Long)

		solution.feedback = returnedMap.get('testDetail')
		solution.score = returnedMap.get('testScore')

		solution.save(flush: true)
	}
}
