<g:if test="${assignmentSolutions}">
	<h4>Trabajos prácticos</h4>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Fecha vencimiento</th>
				<th>Fecha entrega</th>
				<th>Calificación</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${assignmentSolutions}" var="solution">
			<tr>
				<td>
					<g:link controller="assignment" action="show" id="${solution.assignment.id}">
						${solution.assignment.title}
					</g:link>
				</td>
				<td>
					<braid:dateInZone date="${solution.assignment.dueDate}"/>
				</td>
				<td>
					<braid:dateInZone date="${solution.dateCreated}"/>
				</td>
				<td>
					${(solution.score)?"${solution.score}/10.0":'n/a'}
				</td>
				<td>
					<g:link class="btn btn-mini pull-right" controller="assignment" action="feedback" id="${solution.id}">
						Detalles
					</g:link>
				</td>
			</tr>
			</g:each>
		</tbody>
	</table>
</g:if>