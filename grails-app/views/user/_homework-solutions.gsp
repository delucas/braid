<g:if test="${homeworkSolutions}">
	<h4>Tareas</h4>
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
			<g:each in="${homeworkSolutions}" var="solution">
			<tr>
				<td>
					<g:link controller="homework" action="show" id="${solution.homework.id}">
						${solution.homework.title}
					</g:link>
				</td>
				<td>
					<g:formatDate date="${solution.homework.dueDate}" timeZone="America/Argentina/Buenos_Aires"/>
				</td>
				<td>
					<g:formatDate date="${solution.dateCreated}" timeZone="America/Argentina/Buenos_Aires"/>
				</td>
				<td>
					${(solution.feedback?.score)?"${solution.feedback?.score}/3":'n/a'}
				</td>
				<td>
					<g:link class="btn btn-mini pull-right" controller="homework" action="show" id="${solution.homework.id}">
						Detalles
					</g:link>
				</td>
			</tr>
			</g:each>
		</tbody>
	</table>
</g:if>