package braid

class QuestionTagLib {

	static namespace = 'braid'
	def question = { attrs, body ->
		Integer stars = attrs.level
		Integer noStars = 3 - stars
		List tags = ['OOP', 'Testing']
		
		out << '<div class="well span8">'
		out << '<div class="row-fluid">'
		
		out << '<div class="span3">'
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
		
		out << markdown.renderHtml(){ body() }
		
		out << '</div>'
	}
}
