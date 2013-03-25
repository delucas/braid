import braid.Assignment
import braid.AssignmentSolution
import braid.Feedback;
import braid.Homework
import braid.HomeworkSolution
import braid.github.Repository

load "announcementFixtures"

fixture{

	hw1(Homework,
		title: 'Semana 1',
		wording: '''Explicar en dos párrafos el concepto de **Single Responsibility Principle**''',
		dueDate: new Date() - 10,
		course: taller)
	
	fb1(Feedback,
		text: '''Es una buena visión, pero **no** es correcta.''',
		score: 1)
	
	hws1(HomeworkSolution,
		text: '''Esta es mi solución para la pregunta de SRP: Es lo mejor que me pasó en la vida!''',
		feedback: fb1,
		dateCreated: new Date() - 11,
		user: anakin,
		homework: hw1)
	
	hw2(Homework,
		title: 'Semana 2',
		wording: '''Explicar en dos párrafos el concepto de **Open Closed**''',
		dueDate: new Date() - 3,
		course: taller)
	
	hws2(HomeworkSolution,
		text: '''Esta es mi solución para la **segunda** pregunta: Blah!''',
		dateCreated: new Date() - 4,
		user: anakin,
		homework: hw2)
	
	hw3(Homework,
		title: 'Semana 3',
		wording: '''Explicar en dos párrafos el concepto de **Encapsulamiento**''',
		dueDate: new Date() + 4,
		course: taller)
	
	a1(Assignment,
		title: 'Piedra, papel, tijera, lagarto, Spock',
		repo: new Repository(user:'tallerweb', name:'ecuaciones'),
		dueDate: new Date() + 7,
		course: taller)
	
	as1(AssignmentSolution,
		assignment: a1,
		user: anakin,
		dateCreated: new Date() - 1,
		score: 8.3,
		feedback: 'Errores mínimos',
		commit: 'adfeef3436744dfee232ae'
		)
	
	a2(Assignment,
		title: 'Cuentas bancarias',
		repo: new Repository(user:'tallerweb', name:'ecuaciones'),
		dueDate: new Date() + 28,
		course: taller)
	
	a3(Assignment,
		title: 'Ecuaciones',
		repo: new Repository(user:'tallerweb', name:'ecuaciones'),
		dueDate: new Date() + 56,
		course: taller)
	
}