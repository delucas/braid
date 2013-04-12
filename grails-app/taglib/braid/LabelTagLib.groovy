package braid

class LabelTagLib {

	static namespace = 'braid'
	
	def roleLabel = { attrs ->
		
		def role = attrs.role
		
		out << "<span class='label label-info'>${role.replaceAll('ROLE_','')}</span>"
		
	}
	
	def statusHomework = { attrs ->
		
		def solved = attrs.solved
		
		if (solved) {
			out << '<label class="label label-success">Entregada</label>'
		} else {
			out << '<label class="label label-warning">Pendiente</label>'
		}
	}

}
