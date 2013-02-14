import braid.Course
import braid.Link
import braid.UserCourse

load "userFixtures"

fixture{
	
		taller(Course,
		name:'Taller Web 1 - Java - otoño 2013',
		university:'Universidad Nacional de La Matanza',
		syllabus:'''## some markdown
yes
### more
yes, more''',
		honorCode:'''Con el fin de garantizar la equidad, todos los estudiantes que participan en este curso deben estar de acuerdo con cumplir el *Código de Honor*.

1. Mis respuestas a las tareas, pruebas y todo tipo de evaluaciones serán fruto de mi propio trabajo (excepto aquellas que permiten o solicitan explícitamente la colaboración)
2. No voy a poner las soluciones a las tareas, pruebas o evaluaciones a disposición de cualquier otra persona. Esto incluye tanto las soluciones escritas por mí, como aquellas proporcionadas por la cátedra.
3. No voy a participar en ninguna actividad deshonesta que mejorase mis resultados o que pueda afectar de cualquier modo los resultados de otros estudiantes.''')
	
		uc1(UserCourse, user: obi, course: taller, approved: true)
		uc2(UserCourse, user: anakin, course: taller, approved: true)
//		uc3(UserCourse, user: jarjar, course: taller)
		
		link1(Link,
			caption: 'Grupo',
			url: 'https://groups.google.com/forum/?fromgroups#!forum/tallerwebjava',
			position: 1,
			course: taller)
		link2(Link,
			caption: 'Repo Github',
			url: 'https://github.com/tallerweb',
			position: 2,
			course: taller)
		link3(Link,
			caption: '"Notas" del Taller',
			url: 'https://github.com/tallerweb/notas',
			position: 3,
			course: taller)
		link4(Link,
			caption: 'Wiki',
			url: 'https://github.com/tallerweb/notas/wiki',
			position: 4,
			course: taller)
		link5(Link,
			caption: 'Feedback',
			url: 'https://docs.google.com/spreadsheet/viewform?formkey=dDdYNnIzRWFiV2FwbGtyNjA3a2NFZGc6MQ#gid=0',
			position: 5,
			course: taller)
		
	}
