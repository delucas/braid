package braid.filters

import braid.course.Course
import braid.presenters.course.CoursePresenter;

class CourseFilters {

	def courseService

	def filters = {
		addCoursePresenterToModel(controller: 'githubMock|registerUser', action: '*', invert: true) {
			after = { model ->
				if (model) model.coursePresenter = new CoursePresenter(course: courseService.currentCourse)
				if (model) model.allCourses = courseService.allCourses
			}
		}
	}
}
