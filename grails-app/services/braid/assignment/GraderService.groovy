package braid.assignment

import braid.assignment.AssignmentSolution;
import grails.converters.JSON

class GraderService {

	def send(AssignmentSolution solution){

		def map = [:]
		map << ['solutionId': solution.id]
		map << ['githubUser': solution.user.username]
		map << ['nombreTarea': solution.assignment.repo.name]

		rabbitSend 'graderQueue', (map as JSON) as String
	}
}
