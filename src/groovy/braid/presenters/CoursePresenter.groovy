package braid.presenters

import braid.Link
import braid.course.Option

class CoursePresenter {

	def course

	def hasOption(Option option) {
		course.settings.has(option)
	}

	def getOptions() {
		def options = []
		Option.values().each { option ->
			if (option.value & course.options) {
				options << option
			}
		}
		options
	}

	def getName() {
		course.name
	}

	def getUniversity() {
		course.university
	}

	def getLinks() {
		Link.findAllByCourse(course, [sort:'position'])
	}

	def getGithubUsername() {
		course.settings.githubUsername
	}

	def getBannerColor() {
		course.settings.bannerColor
	}

}
