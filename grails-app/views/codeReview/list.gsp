<html>
<head>

<title>revisiones de código</title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />
</head>
<body>

	<div class="span12">
		<legend>Revisiones de código</legend>

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
					<th>Entrega</th>
					<th>Revisión</th>
					<th>Autocorrección</th>
					<th>&nbsp;</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Contador de ganado</td>
					<td>
						<g:formatDate date="${new Date() - 10}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${new Date() - 3}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${new Date() + 4}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:link action="self" class="btn">Autocorregir</g:link>
					</td>
				</tr>
				<tr>
					<td>Tanteador de básquet</td>
					<td>
						<g:formatDate date="${new Date() - 3}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${new Date() + 4}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${new Date() + 11}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:link action="pair" class="btn">Revisar soluciones</g:link>
					</td>
				</tr>
				<tr>
					<td>La orquesta</td>
					<td>
						<g:formatDate date="${new Date() + 4}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${new Date() + 11}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:formatDate date="${new Date() + 18}"
								timeZone="America/Argentina/Buenos_Aires" />
					</td>
					<td>
						<g:link action="solve" class="btn">Resolver</g:link>
					</td>
				</tr>
				
			</tbody>
		</table>
	</div>

</body>
</html>