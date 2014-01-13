<html>
<head>

<title>tareas</title>
<meta name="layout" content="main">

<parameter name="homeworks" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Tareas 
		
			<sec:ifAllGranted roles="ROLE_JEDI">
				<g:link controller="homework" action="create" class="btn btn-success pull-right">
					Nueva tarea
				</g:link>
			</sec:ifAllGranted>
		
		</legend>

		<div class="alert alert-info">
			<strong><g:message code="braid.homework.dueDates.title"/></strong>
			<p><g:message code="braid.homework.dueDates.message"/></p>
		</div>

		<g:if test="${presenter.homeworkList}">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Título</th>
						<th>Fecha de entrega</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${presenter.homeworkList}" var="homework">
						<tr>
							<td>
								${homework.title}
							</td>
							<td><g:formatDate date="${homework.dueDate}"
									timeZone="America/Argentina/Buenos_Aires" /></td>
							<td>
								<g:link class="btn btn-small" action="show" id="${homework.id}">
									Ver detalles
								</g:link>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</g:if>
		<g:else>
			<div class="alert alert-info">
				<g:message code="braid.homework.none.message"/>
			</div>
		</g:else>
	</div>

</body>
</html>