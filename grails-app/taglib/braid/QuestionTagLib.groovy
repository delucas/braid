package braid

class QuestionTagLib {

	def userService
	
	static namespace = 'braid'
	def question = { attrs ->
		
		Question question = attrs.question
		
		Integer stars = question.level
		Integer noStars = 3 - stars
		
		def tags = question.tags
		
		out << '<div class="well span8">'
		out << '<div class="row-fluid">'
		
		out << '<div class="span3">'
		out << "#${question.id} - "
		stars.times {
			out << '<i class="icon-star"></i>'
		}
		noStars.times {
			out << '<i class="icon-star-empty"></i>'
		}
		out << '</div>'
		
		out << '<div class="span9">'
		tags.each {
			out << '<span class="label label-info pull-right">' + it + '</span>'
		}
		out << '</div>'
		
		out << '</div>'
		
		
		out << '<div class="md">'
		out << markdown.renderHtml(){ question.wording }
		out << '</div>'
		
//		TODO: habilitar cuando se defina política de edición de preguntas
//		if (userService.currentUser.hasRole('JEDI')) {
//			
//			out << '<div class="row-fluid"><div class="pull-right">'
//			
//			out << g.link(controller:'question', action:'edit', 'class':'btn btn-small', title:'Editar'){'<i class="icon-edit"></i>'}
//			out << g.link(controller:'question', action:'delete', 'class':'btn btn-small btn-danger', title:'Eliminar'){'<i class="icon-remove icon-white"></i>'}
//			
//			out << '</div></div>'
//			
//		}
		
		
		out << '</div>'
	}
}
