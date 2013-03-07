package braid

class AssignmentTagLib {

	def userService
	
	static namespace = 'assignment'
	
	def wording = { attrs ->
		
		def assignment = attrs.assignment
		
		try {
			
			def wording = assignment.wording
		
			out << '<section class="headless-wording md">'
			out << markdown.renderHtml(){wording}
			out << '</section>'
			
		} catch (UnknownHostException e) {
		
			out << '<div class="alert alert-error">'
			out << '	<strong>Oops! Parece que algo no salió como se esperaba...</strong>'
			out << '	<p>No se pudo cargar la consigna desde el repositorio. Probablemente Github se encuentre caído.</p>'
			out << '	<p>Por favor, inténtelo nuevamente más tarde.</p>'
			out << '</div>'
			
		}
	}
	
}
