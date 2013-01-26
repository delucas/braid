package braid

class HomeworkTagLib {

	def userService
	
	static namespace = 'homework'
	
	def wording = { attrs ->
		
		Homework homework = attrs.homework
		
		out << '<div class="well">'
		out << '	<legend>Consigna</legend>'
		out << '	<div class="well">'
		out << markdown.renderHtml(){homework.wording}
		out << '	</div>'
		out << '</div>'
	}
}
