import braid.assignment.Assignment
import braid.assignment.AssignmentSolution
import braid.homework.Feedback
import braid.homework.Homework
import braid.homework.HomeworkSolution
import braid.User;
import braid.github.Repository
import braid.reviews.CodeReview
import braid.reviews.CodeReviewHomework
import braid.reviews.CodeReviewSolution

load "announcementFixtures"

fixture{

	hw1(Homework,
		title: 'Semana 1',
		wording: '''Explicar en dos párrafos el concepto de **Single Responsibility Principle**''',
		startDate: new Date() - 15,
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
		startDate: new Date() - 8,
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
		startDate: new Date() - 1,
		dueDate: new Date() + 4,
		course: taller)

	hw4(Homework,
		title: 'Semana 4',
		wording: '''Explicar en dos párrafos el concepto de **Polimorfismo**''',
		startDate: new Date() + 3,
		dueDate: new Date() + 13,
		course: taller)

	a1(Assignment,
		title: 'Piedra, papel, tijera, lagarto, Spock',
		repo: new Repository(user:'tallerweb', name:'piedra-papel-tijera-lagarto-Spock'),
		startDate: new Date() - 49,
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
		startDate: new Date() - 28,
		dueDate: new Date() + 28,
		course: taller)

	a3(Assignment,
		title: 'Ecuaciones',
		repo: new Repository(user:'tallerweb', name:'ecuaciones'),
		startDate: new Date() - 1,
		dueDate: new Date() + 56,
		course: taller)

	a4(Assignment,
		title: 'Fractales',
		repo: new Repository(user:'tallerweb', name:'fractales'),
		startDate: new Date() + 7,
		dueDate: new Date() + 70,
		course: taller)

	crh1(CodeReviewHomework,
		title: 'La orquesta',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		startDate: new Date() - 3,
		solutionDueDate: new Date() + 4,
		reviewDueDate: new Date() + 11,
		course: taller)

	crs1(CodeReviewSolution,
		homework: crh1,
		user: leia,
		gist: 5471002)

	crh2(CodeReviewHomework,
		title: 'Contador de ganado',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		startDate: new Date() - 7,
		solutionDueDate: new Date() - 3,
		reviewDueDate: new Date() + 4,
		requiredReviews: 1,
		course: taller)

	crs2(CodeReviewSolution,
		homework: crh2,
		user: anakin,
		gist: 765432)

	crs3(CodeReviewSolution,
		homework: crh2,
		user: leia,
		gist: 765433)

	cr1(CodeReview,
		user: anakin,
		solution: crs3,
		clarity: 2,
		conventions: 2,
		correctness: 3,
		tests: 0,
		comments: 'Creo que este código es bastante promedio, no está a la altura de un Skywalker',
		best: 'Lo mejor fue cuando dejó de codear',
		advice: 'Mejorá tus convenciones, porque otros programadores no podemos entenderte')

	cr2(CodeReview,
		solution: crs3,
		user: leia,
		clarity: 3,
		conventions: 3,
		correctness: 3,
		tests: 0,
		comments: 'Creo que este código es muy bueno',
		best: 'Es muy legible, y eso me enorgullece',
		advice: 'Quizás podría haber hecho más corto el código para poder tipear menos')


	crh3(CodeReviewHomework,
		title: 'Tanteador de básquet',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		startDate: new Date() - 14,
		solutionDueDate: new Date() - 10,
		reviewDueDate: new Date() - 3,
		course: taller)

	crs4(CodeReviewSolution,
		homework: crh3,
		user: leia,
		gist: 5431625)

	crs5(CodeReviewSolution,
		homework: crh3,
		user: anakin,
		gist: 5471052)

	cr3(CodeReview,
		user: anakin,
		solution: crs4,
		clarity: 2,
		conventions: 2,
		correctness: 3,
		tests: 0,
		comments: 'Creo que este código es bastante promedio, no está a la altura de un Skywalker',
		best: 'Lo mejor fue cuando dejó de codear',
		advice: 'Mejorá tus convenciones, porque otros programadores no podemos entenderte')

	cr4(CodeReview,
		user: leia,
		solution: crs4,
		clarity: 3,
		conventions: 3,
		correctness: 3,
		tests: 0,
		comments: 'Creo que este código es muy bueno',
		best: 'Es muy legible, y eso me enorgullece',
		advice: 'Quizás podría haber hecho más corto el código para poder tipear menos')

	cr6(CodeReview,
		user: leia,
		solution: crs5,
		clarity: 2,
		conventions: 2,
		correctness: 3,
		tests: 0,
		comments: 'Creo que este código es bastante promedio, no está a la altura de un Skywalker',
		best: 'Lo mejor fue cuando dejó de codear',
		advice: 'Mejorá tus convenciones, porque otros programadores no podemos entenderte')

	cr5(CodeReview,
		user: anakin,
		solution: crs5,
		clarity: 3,
		conventions: 3,
		correctness: 3,
		tests: 0,
		comments: 'Creo que este código es muy bueno',
		best: 'Es muy legible, y eso me enorgullece',
		advice: 'Quizás podría haber hecho más corto el código para poder tipear menos')

	crh4(CodeReviewHomework,
		title: 'Filarmónica inédita',
		wording: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed. Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec vehicula felis tortor. Vestibulum imperdiet est tempus purus accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus, euismod scelerisque quam euismod in. Phasellus venenatis mollis risus vitae porta.',
		startDate: new Date() + 2,
		solutionDueDate: new Date() + 9,
		reviewDueDate: new Date() + 13,
		course: taller)

}
