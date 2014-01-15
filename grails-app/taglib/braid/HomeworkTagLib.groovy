package braid

class HomeworkTagLib {

	def userService
	
	static namespace = 'homework'
	
	def wording = { attrs ->
		
		def homework = attrs.homework
		
		out << '<div class="well">'
		out << '	<legend>Consigna</legend>'
		out << '	<div class="well md">'
		out << homework.wording
		out << '	</div>'
		out << '</div>'
	}
	
	def solution = { attrs ->
		
		def solution = attrs.solution
		
		out << '<div class="well">'
		out << "	<legend>Respuesta de ${solution.user.name}</legend>"
		out << '	<div class="well md">'
		out << solution.text
		out << '	</div>'
		out << '</div>'
		
	}
	
	def feedback = { attrs ->
		
		def solution = attrs.solution
		
		if (solution?.feedback) {
			out << '<div class="well">'
			out << "	<legend>Feedback <small>(${solution.feedback.score}/3)</small></legend>"
			out << '	<div class="well md">'
			out << solution.feedback.text
			out << "		<small class='pull-right'>revisado por ${solution.reviewer.name}</small>"
			out << '	</div>'
			out << '</div>'
		}
	}
}
