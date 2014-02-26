<html>
<head>
<title><g:message code="braid.courses.title" /></title>
<meta name="layout" content="main-no-menu">
</head>

<body>

	<div class="row-fluid">
		<legend>
			<g:message code="braid.courses.legend.list" />

			<g:if test="${presenter.canCreate()}">
				<g:link class="btn btn-success pull-right" action="create">
					Nuevo curso
				</g:link>
			</g:if>

		</legend>

		<g:if test="${presenter.canJoin()}">
			<div class="alert alert-info">
				<p>
					<g:message code="braid.course.none.message" />
				</p>
			</div>
		</g:if>

		<g:if test="${presenter.courses}">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th><g:message code="braid.course.university.title" /></th>
						<th><g:message code="braid.course.course.title" /></th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${presenter.courses}" var="course">
						<tr>
							<td>
								${course.university}
							</td>
							<td>
								${course.name}
							</td>
							<td><g:if test="${presenter.canJoin()}">
									<g:link class="btn btn-small" action="join" id="${course.id}">
										Inscribirme
									</g:link>
								</g:if> <g:if test="${presenter.canAdmin()}">
									<g:link class="btn btn-small" action="students" id="${course.id}">
										Administrar
									</g:link>
								</g:if></td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</g:if>
		<g:else>
			<div class="alert alert-info">
				<g:message code="braid.courses.none.message" />
			</div>
		</g:else>
	</div>

</body>
</html>
