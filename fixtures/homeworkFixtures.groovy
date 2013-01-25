import java.util.Date;

import braid.Homework
import braid.HomeworkSolution;
import braid.User;

load "announcementFixtures"

fixture{

	hw1(Homework,
		title: 'Semana 1',
		wording: '''Explicar en dos párrafos el concepto de **Single Responsibility Principle**''',
		dueDate: new Date() - 3,
		course: taller)
	
	hws1(HomeworkSolution,
		text: '''Esta es mi solución para la pregunta de SRP: Es lo mejor que me pasó en la vida!''',
		dateCreated: new Date() - 4,
		user: anakin,
		homework: hw1)
	
	hw2(Homework,
		title: 'Semana 2',
		wording: '''Explicar en dos párrafos el concepto de **Open Closed**''',
		dueDate: new Date() + 4,
		course: taller)
	
	hw3(Homework,
		title: 'Semana 3',
		wording: '''Explicar en dos párrafos el concepto de **Encapsulamiento**''',
		dueDate: new Date() + 11,
		course: taller)
		
}