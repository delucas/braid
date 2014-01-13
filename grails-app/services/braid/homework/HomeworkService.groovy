package braid.homework

import braid.homework.Homework;
import braid.homework.HomeworkSolution;

class HomeworkService {


	def countByHomeworkAndNotFeedback(Homework homework) {
		
		def criteria = HomeworkSolution.createCriteria()
		
		criteria.count() {
			eq('homework', homework)
			isNull('feedback')
		}
	}
	
	def findAllByHomework(Homework homework) {
		HomeworkSolution.findAllByHomework(homework, [sort: 'dateCreated', order: 'desc'])
	}
	
}
