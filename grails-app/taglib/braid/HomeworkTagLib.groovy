package braid

class HomeworkTagLib {

	def userService
	
	static namespace = 'homework'
	
	def wording = { attrs ->
		
		def homework = attrs.homework
		
		out << '<div class="well">'
		out << '	<legend>Consigna</legend>'
		out << '	<div class="well md">'
		out << markdown.renderHtml(){ homework.wording }
		out << '	</div>'
		out << '</div>'
	}
	
	def solution = { attrs ->
		
		def solution = attrs.solution
		
		out << '<div class="well">'
		out << "	<legend>Respuesta de ${solution.user.name}</legend>"
		out << '	<div class="well md">'
		out << markdown.renderHtml(){ solution.text }
		out << '	</div>'
		out << '</div>'
		
	}
	
	def feedback = { attrs ->
		
		def solution = attrs.solution
		
		if (solution?.feedback) {
			out << '<div class="well">'
			out << '	<legend>Feedback</legend>'
			out << '	<div class="well md">'
			out << markdown.renderHtml(){ solution.feedback }
			out << '	</div>'
			out << '</div>'
		}
	}
}
