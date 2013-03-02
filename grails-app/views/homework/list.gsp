<html>
<head>

<title>tareas</title>
<meta name="layout" content="main">

<parameter name="homeworks" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Tareas 
		
			<sec:ifAllGranted roles="JEDI">
				<g:link controller="homework" action="create" class="btn btn-success pull-right">
					Nueva tarea
				</g:link>
			</sec:ifAllGranted>
		
		</legend>

		<div class="alert alert-info">
			<strong>Sobre las fechas de entrega</strong>
			<p>Recordá que las fechas de entrega son estrictas, y no se
				modifican por ninguna razón. Podrás mirar las consignas durante toda
				la cursada, pero sólo podrás resolverlas antes de la fecha de
				entrega.</p>
		</div>

		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>Título</th>
					<th>Fecha de entrega</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${homeworks}" var="homework">
					<tr>
						<td>
							${homework.title}
						</td>
						<td><g:formatDate date="${homework.dueDate}"
								timeZone="America/Argentina/Buenos_Aires" /></td>
						<td><g:link class="btn btn-small" action="show"
								id="${homework.id}">
			  				Ver detalles
			  			</g:link></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>

</body>
</html>