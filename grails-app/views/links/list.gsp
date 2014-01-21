<html>
<head>

<title>tareas</title>
<meta name="layout" content="main">

<parameter name="links" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Enlaces 
			<g:link controller="links" action="create" class="btn btn-success pull-right">
				Nuevo enlace
			</g:link>
		</legend>

		<g:if test="${presenter.linksList}">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Etiqueta</th>
						<th>URL</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${presenter.linksList}" var="link">
						<tr>
							<td>
								${link.caption}
							</td>
							<td>
								${link.url}
							</td>
							<td>
								<div class="btn-group">
									<g:link action="moveUp" id="${link.id}" class="btn btn-mini"><i class="icon icon-chevron-up"></i></g:link>
									<g:link action="moveDown" id="${link.id}" class="btn btn-mini"><i class="icon icon-chevron-down"></i></g:link>
									<g:link action="delete" id="${link.id}" class="btn btn-mini btn-danger"><i class="icon icon-white icon-trash"></i> Eliminar</g:link>
								</div>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</g:if>
		<g:else>
			<div class="alert alert-info">
				<g:message code="braid.links.none.message"/>
			</div>
		</g:else>
	</div>

</body>
</html>