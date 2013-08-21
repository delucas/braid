<html>
<head>
	<title><g:message code="braid.courses.title"/></title>
	<meta name="layout" content="main-no-menu">
</head>

<body>

	<div class="span12">
		<legend>
			<g:message code="braid.courses.legend.list"/>
		</legend>

		<div class="alert alert-info">
			<p><g:message code="braid.course.none.message"/></p>
		</div>

		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th><g:message code="braid.course.course.university"/></th>
					<th><g:message code="braid.course.course.title"/></th>
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
							Inscribirme
			  			</g:link></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>

</body>
</html>
