package braid.filters

import braid.course.Course
import braid.presenters.CoursePresenter

class CourseFilters {
	def filters = {
		addCoursePresenterToModel(controller: '*', action: '*') {
			after = { model ->
				if (model) model.coursePresenter = new CoursePresenter(course: Course.list()[0])
			}
		}
	}
}
