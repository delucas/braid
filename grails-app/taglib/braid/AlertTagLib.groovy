package braid

class AlertTagLib {

	static namespace = 'braid'
	
	def alertError = { attrs ->
		
		def command = attrs.command
		
		if (command?.hasErrors()) {
			out << '<div class="alert alert-error">'
			out << '	<strong>Oops! Parece que algo no salió como se esperaba...</strong>'
			out << '	<p>'
			out << g.renderErrors(bean: command)
			out << '	</p>'
			out << '</div>'
		}
	}
	
	def alertInfo = { attrs, body ->
		
		def title = attrs.title
		def message = body()
		
		out << '<div class="alert alert-info">'
		out << "	<strong>${title}</strong>"
		out << '	<p>'
		out << message
		out << '	</p>'
		out << '</div>'
		
	}
}
