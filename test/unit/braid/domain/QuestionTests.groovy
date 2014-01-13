package braid.domain

import grails.test.mixin.*
import grails.test.mixin.support.*

import org.junit.*

import braid.Question
import braid.homework.Feedback;

@TestFor(Question)
class QuestionTests {

	void testQuestionHasTags() {
		Question aQuestion = new Question()
		aQuestion.addToTags('tag')
		
		assert aQuestion.tags.size() == 1
	}
	
}
