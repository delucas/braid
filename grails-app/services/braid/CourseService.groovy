package braid

class CourseService {

    def getCurrentCourse() {
		// TODO: resolver cuando sea multicurso
		Course.list().asList()[0]
	}
}
