package braid

class FormCommonsTagLib {

	def userService
	
	static namespace = 'braid'
	
	def textArea = { attrs ->
		
		def bean = attrs.bean
		String name = attrs.name
		String elementId = attrs.id
		String beanField = attrs.beanField
		
		out << g.textArea(rows: 7, 'class': "textarea span12 wmd-panel ${hasErrors(bean:bean, field:beanField, 'error')}", name: name, id: elementId) {bean?."${beanField}"}
		
	}
}
