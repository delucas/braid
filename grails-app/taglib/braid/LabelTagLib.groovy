package braid

class LabelTagLib {

	static namespace = 'braid'
	
	def statusHomework = { attrs ->
		
		def solved = attrs.solved
		
		if (solved) {
			out << '<label class="label label-success">Entregada</label>'
		} else {
			out << '<label class="label label-warning">Pendiente</label>'
		}
	}

}
