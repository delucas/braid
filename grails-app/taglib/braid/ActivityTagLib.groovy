package braid

class ActivityTagLib {

	static namespace = 'braid'
	
	def activity = { attrs ->
		
		String type = attrs.type
		String name = attrs.what
		Date when = attrs.when
		
		out << '<div class="well">'
		out << '	<div>'
		out << "Resolviste ${phrase(type)}<br><strong>${name}</strong>" 
		out << '	</div>'
		out << '	<div>'
		out << '		<small>' + g.formatDate(date: when, timeZone: "America/Argentina/Buenos_Aires") + '</small>'
		out << '	</div>'
		out << '</div>'
		
	}
	
	private phrase(def type) {
		if ('homework' == type) {
			'la tarea'
		} else {
			'el trabajo práctico'
		}
	}
}
