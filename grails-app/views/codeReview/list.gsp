<html>
<head>

<title>revisiones de código</title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />
</head>
<body>

	<div class="span12">
		<legend>Revisiones de código</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="¡Muy buen trabajo!">
				${flash.message}
			</braid:alertInfo>
		</g:if>

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
					<th>Fecha límite de entrega</th>
					<th>Fecha límite de revisión</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${homeworkList}" var="homework">
				<tr>
					<td>${homework.title}</td>
					<td>
						<g:formatDate date="${homework.solutionDueDate}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${homework.reviewDueDate}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<codeReview:detailsLink homework="${homework}"/>
					</td>
				</tr>
				</g:each>
			</tbody>
		</table>
	</div>

</body>
</html>