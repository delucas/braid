package braid.presenters

import braid.course.Option;
import groovy.time.TimeCategory


class CoursePresenter {

	def course
	
	def getOptions() {
		def options = []
		Option.values().each { option ->
			if (option.value & course.options) {
				options << option
			}
		}
		options
	}

	def getGithubUsername() {
		course.settings.githubUsername
	}
	
	def getBannerColor() {
		course.settings.bannerColor
	}
	
	def getBanner() {
		"${course.university} - ${course.name}"
	}
	
}
