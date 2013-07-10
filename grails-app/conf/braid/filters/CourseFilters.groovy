package braid.filters

import braid.course.Course
import braid.presenters.CoursePresenter

class CourseFilters {

	def courseService

	def filters = {
		addCoursePresenterToModel(controller: 'githubMock', action: '*', invert: true) {
			after = { model ->
				if (model) model.coursePresenter = new CoursePresenter(course: courseService.currentCourse)
			}
		}
	}
}
