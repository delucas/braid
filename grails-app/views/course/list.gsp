<html>
<head>
	<title>cursos</title>
	<meta name="layout" content="main-no-menu">
</head>

<body>

	<div class="span12"><legend> Cursos activos</legend>

		<div class="alert alert-info">
			<p>Actualmente no te encontrás inscripto a ningún curso. Por
				favor, seleccioná uno del listado</p>
		</div>

		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>Universidad</th>
					<th>Curso</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${courses}" var="course">
					<tr>
						<td>
							${course.university}
						</td>
						<td>
							${course.name}
						</td>
						<td><g:link class="btn btn-small" action="join"
							id="${course.id}">
			  				Inscribirse
			  			</g:link></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>

</body>
</html>