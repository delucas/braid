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
yes, more''')
	
		uc1(UserCourse, user: obi, course: taller, approved: true)
		uc2(UserCourse, user: anakin, course: taller, approved: true)
		uc3(UserCourse, user: jarjar, course: taller)
		
	}