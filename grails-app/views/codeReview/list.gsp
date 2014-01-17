<html>
<head>

<title><g:message code="braid.reviews.title"/></title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			<g:message code="braid.reviews.legend.list"/>
		</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>

		<div class="alert alert-info">
			<strong><g:message code="braid.homework.dueDates.title"/></strong>
			<p><g:message code="braid.homework.dueDates.message"/></p>
		</div>

		<g:if test="${presenter.homeworkList}">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>Título</th>
						<th>Estado</th>
						<th>Fecha límite de entrega</th>
						<th>Fecha límite de revisión</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${presenter.homeworkList}" var="homework">
					<tr>
						<td>${homework.title}</td>
						<td>
							<braid:homeworkStage homework="${homework}"/>
						</td>
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
		</g:if>
		<g:else>
			<div class="alert alert-info">
				<g:message code="braid.codeReview.none.message"/>
			</div>
		</g:else>
	</div>

</body>
</html>