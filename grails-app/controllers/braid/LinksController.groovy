package braid

import java.util.Date;

import grails.plugins.springsecurity.Secured
import grails.validation.Validateable;
import braid.helpers.ListSorter

class LinksController {

	def courseService

	@Secured(['ROLE_JEDI'])
	def list() {
		[presenter: [linksList: Link.byCourse(courseService.currentCourse).list([sort: 'position'])]]
	}

	@Secured(['ROLE_JEDI'])
	def moveUp(Long id) {

		moveElement(id, { links, position -> ListSorter.shiftUp(links, position)})
		redirect action: 'list'
	}

	@Secured(['ROLE_JEDI'])
	def moveDown(Long id) {

		moveElement(id, { links, position -> ListSorter.shiftDown(links, position)})
		redirect action: 'list'
	}

	// TODO: move to service
	private moveElement(Long id, operation) {
		def links = Link.byCourse(courseService.currentCourse).list([sort: 'position'])
		def theLink = Link.get(id)
		def position = links.findIndexOf { it == theLink }

		def orderedList = operation(links, position)
		orderedList.eachWithIndex { Link link, idx ->
			link.position = idx
			link.save()
		}
	}

	@Secured(['ROLE_JEDI'])
	def delete(Long id) {
		def theLink = Link.get(id)
		theLink.delete()

		redirect action: 'list'
	}

	@Secured(['ROLE_JEDI'])
	def create() {
	}

	@Secured(['ROLE_JEDI'])
	def save(LinkCommand command) {

		if (command.validate()) {

			def course = courseService.currentCourse

			def link = new Link(command.properties)
			link.course = course
			def maxPosition = Link.createCriteria().get {
			    projections {
			        max 'position'
			    }
				eq 'course', course
			} as Long

			link.position = maxPosition + 1

			link.save(flush: true)

			flash.message = 'Se ha creado correctamente el enlace'
			redirect(action:'list')

		} else {
			render view:'create', model: [
				link: command
			]
		}

	}

	@Secured(['ROLE_JEDI'])
	def edit(Long id) {
		[link: Link.get(id)]
	}

	@Secured(['ROLE_JEDI'])
	def update(LinkCommand command) {

		if (command.validate()) {

			def link = Link.get(params.id)
			link.properties = command.properties
			link.save(flush: true)

			flash.message = 'Se ha creado correctamente el enlace'
			redirect(action:'list')

		} else {
			render view:'create', model: [
				link: command
			]
		}

	}

}

@Validateable
class LinkCommand {

	String caption
	String url

	static constraints = {
		caption blank: false
		url url: true
	}

}