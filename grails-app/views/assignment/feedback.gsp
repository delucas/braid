<html>
<head>
<title>feedback: ${solution.assignment.title}</title>
<meta name="layout" content="main">
<parameter name="assignments" value="active" />

<r:require modules="bootstrap-modal" />
<r:require module="markdown" />
</head>

<body>

	<div class="span12">

		<legend>
			Feedback para "${solution.assignment.title}"
		</legend>

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Fecha de la solución</th>
					<th>Calificación</th>
					<th>Detalles</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td><g:formatDate date="${solution.dateCreated}"
							timeZone="America/Argentina/Buenos_Aires"/></td>
					<td>${solution.score}/10.00</td>
					<td>
						<pre>${solution.feedback}</pre>
					</td>
				</tr>
			</tbody>
		</table>

	</div>

</body>
</html>

