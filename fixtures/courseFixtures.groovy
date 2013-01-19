import braid.Course
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
		honorCode:'''# Código de Honor
Con el fin de garantizar la equidad, todos los estudiantes que participan en este curso deben estar de acuerdo con cumplir con el *Código de Honor*.
1. Mis respuestas a las tareas, pruebas y todo tipo de evaluaciones serán fruto de **mi propio trabajo** (a excepción de aquellas que permiten o solicitan explícitamente la colaboración)
2. No voy a poner las soluciones a las tareas, pruebas o evaluaciones a disposición de cualquier otra persona. Esto incluye tanto a las soluciones escritas por mí, como a las soluciones oficiales proporcionadas por la cátedra.
3. No voy a participar en ninguna actividad deshonesta que mejorase mis resultados o que pueda afectar de cualquier modo los resultados de otros estudiantes.''')
	
		uc1(UserCourse, user: obi, course: taller, approved: true)
		uc2(UserCourse, user: anakin, course: taller, approved: true)
//		uc3(UserCourse, user: jarjar, course: taller)
		
	}