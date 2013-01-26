package braid

class FormCommonsTagLib {

	def userService
	
	static namespace = 'braid'
	
	def textArea = { attrs ->
		
		def bean = attrs.bean
		String name = attrs.name
		String elementId = attrs.id
		String beanField = attrs.beanField
		
		out << g.textArea(rows: 7, 'class': "span12 wmd-panel ${hasErrors(bean:bean, field:beanField, 'error')}", name: name, id: elementId) {bean?."${beanField}"}
		out << '''
		<small class="pull-right">
			* Recuerde utilizar 
			<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
				formato markdown
			</a>
		</small>
'''
		
	}
}
