import braid.Assignment
import braid.AssignmentSolution
import braid.Feedback;
import braid.Homework
import braid.HomeworkSolution
import braid.github.Repository
import braid.reviews.CodeReviewHomework;

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
		reviewer: obi,
		homework: hw1)
	
	hw2(Homework,
		title: 'Semana 2',
		wording: '''Explicar en dos párrafos el concepto de **Open Closed**''',
		dueDate: new Date() - 3,
		course: taller)
	
	fb2(Feedback,
		text: '''Es una buena visión, esta vez ES correcta.''',
		score: 3)

	hws2(HomeworkSolution,
		text: '''Esta es mi solución para la **segunda** pregunta: Blah!''',
		dateCreated: new Date() - 4,
		feedback: fb2,
		user: anakin,
		reviewer: obi,
		homework: hw2)
	
	hw3(Homework,
		title: 'Semana 3',
		wording: '''Explicar en dos párrafos el concepto de **Encapsulamiento**''',
		dueDate: new Date() + 4,
		course: taller)
	
	a1(Assignment,
		title: 'Piedra, papel, tijera, lagarto, Spock',
		repo: new Repository(user:'tallerweb', name:'piedra-papel-tijera-lagarto-Spock'),
		dueDate: new Date() + 7,
		course: taller)
	
	as1(AssignmentSolution,
		assignment: a1,
		user: anakin,
		dateCreated: new Date() - 4,
		score: 2.3,
		feedback: 'Errores mínimos',
		commit: 'adfeef3436744dfee232ae'
		)
	as2(AssignmentSolution,
		assignment: a1,
		user: anakin,
		dateCreated: new Date() - 3,
		score: 4.3,
		feedback: 'Errores mínimos',
		commit: 'adfeef3436744dfee232ae'
		)
	as3(AssignmentSolution,
		assignment: a1,
		user: anakin,
		dateCreated: new Date() - 2,
		score: 3.3,
		feedback: 'Errores mínimos',
		commit: 'adfeef3436744dfee232ae'
		)
	as4(AssignmentSolution,
		assignment: a1,
		user: anakin,
		dateCreated: new Date() - 1,
		score: 8.3,
		feedback: 'Errores mínimos',
		commit: 'adfeef3436744dfee232ae'
		)
	
	a2(Assignment,
		title: 'Cuentas bancarias',
		repo: new Repository(user:'tallerweb', name:'cuentas-bancarias'),
		dueDate: new Date() + 28,
		course: taller)
	
	a3(Assignment,
		title: 'Ecuaciones',
		repo: new Repository(user:'tallerweb', name:'ecuaciones'),
		dueDate: new Date() + 56,
		course: taller)

	crh1(CodeReviewHomework,
		title: 'La orquesta',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		solutionDueDate: new Date() + 4,
		reviewDueDate: new Date() + 11,
		course: taller)

	crh2(CodeReviewHomework,
		title: 'Contador de ganado',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		solutionDueDate: new Date() - 3,
		reviewDueDate: new Date() + 4,
		course: taller)
	
	crh3(CodeReviewHomework,
		title: 'Tanteador de básquet',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		solutionDueDate: new Date() - 10,
		reviewDueDate: new Date() - 3,
		course: taller)

}
