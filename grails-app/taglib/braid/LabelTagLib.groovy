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

	def homeworkStage = { attrs ->
		def homework = attrs.homework

		if (homework.outOfDate) {
			out << '<span class="label">Finalizada</span>'
		} else if (homework.published) {
			out << '<span class="label label-success">Vigente</span>'
		} else {
			def startDate = braid.dateInZone(date: homework.startDate)
			out << "<span class='label' title='Se publicará el ${startDate}'><i class='icon-white icon-info-sign'></i> Aún no publicada</span>"
		}
	}

}
