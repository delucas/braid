package braid

class QuestionService {

    def getNextQuestionNumber(){
		def list = Question.executeQuery('select max(q.position) from Question q')
		list ? list[0] + 1 : 1 
	}
}
