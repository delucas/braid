package braid

class HomeworkTagLib {

	def userService
	
	static namespace = 'homework'
	
	def wording = { attrs ->
		
		Homework homework = attrs.homework
		
		out << '<div class="well">'
		out << '	<legend>Consigna</legend>'
		out << '	<div class="well">'
		out << markdown.renderHtml(){ homework.wording }
		out << '	</div>'
		out << '</div>'
	}
	
	def solution = { attrs ->
		
		HomeworkSolution solution = attrs.solution
		
		out << '<div class="well">'
		out << "	<legend>Respuesta de ${solution.user.name}</legend>"
		out << '	<div class="well md">'
		out << markdown.renderHtml(){ solution.text }
		out << '	</div>'
		out << '</div>'
		
	}
}
