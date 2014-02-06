package braid.presenters.course

import braid.Link
import braid.course.Option

class CoursePresenter {

	def course

	def hasOption(Option option) {
		course.settings.has(option)
	}

	def getName() {
		course.name
	}

	def getUniversity() {
		course.university
	}

	def getLinks() {
		Link.byCourse(course).list([sort: 'position'])
	}

	def getGithubUsername() {
		course.settings.githubUsername
	}

	def getBannerColor() {
		course.settings.bannerColor
	}

}
