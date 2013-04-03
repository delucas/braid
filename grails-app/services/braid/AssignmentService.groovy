package braid

import grails.converters.JSON
import grails.plugin.jms.*

class AssignmentService {

	static transactional = true
	static exposes = ['jms']
	def grailsApplication

	@Queue(name='assignment.grader')
	def onMessage(assignmentSolutionId) {
		grade(assignmentSolutionId)
	}


	def grade(Long assignmentSolutionId){

		def solution = AssignmentSolution.get(assignmentSolutionId)

		def map = [:]
		map.put('githubUser', solution.user.username)
		map.put('nombreTarea', solution.assignment.title)

		def correcciones
		def s = crearSocket()

		s.withStreams { input, output ->
			output << (map as JSON)
			output << '\n'
			correcciones = input.newReader().readLine()
		}
		s.close()

		def returnedMap = JSON.parse(correcciones)

		solution.feedback = returnedMap.get('testDetail')
		solution.score = returnedMap.get('testScore').get('score') as Double

		solution.save(flush: true)

	}

	private crearSocket(){

		def graderConf = grailsApplication.config.grader
		def graderHost = graderConf.host
		def graderPort = graderConf.port

		new Socket(graderHost, graderPort);
	}
}
