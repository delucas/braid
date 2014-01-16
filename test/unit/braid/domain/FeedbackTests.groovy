package braid.domain

import grails.test.mixin.support.*

import braid.homework.Feedback;

@TestFor(Feedback)
class FeedbackTests {

	void testHonorsConstraints() {
		
		def feedback = new Feedback()
		mockForConstraintsTests(Feedback, [feedback])
		
		assert !feedback.validate()
		assert 'nullable' == feedback.errors['text']
		assert 'nullable' == feedback.errors['score']

		feedback = new Feedback(text:'Some feedback', score: 0)
		assert !feedback.validate()
		assert 'range' == feedback.errors['score']

		feedback = new Feedback(text:'Some feedback', score: 1)		
		assert feedback.validate()
	}

}
