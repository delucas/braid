package braid.controllers

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import braid.QuestionController
import braid.QuestionService

@grails.test.mixin.TestFor(QuestionController)
@TestMixin(DomainClassUnitTestMixin)
class QuestionControllerTests {

	void testCreateReturnsCorrectModel() {

		def questionServiceMock = mockFor(QuestionService)
		questionServiceMock.demand.getNextQuestionNumber { -> 1}
		controller.questionService = questionServiceMock.createMock()
		
		controller.create()
		
		assert 200 == response.status
		assert '/question/create' == view
		assert 1 == model.nextQuestionNumber

	}

}
