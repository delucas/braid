<html>
<head>
<title><g:message code="braid.courses.students.title" /></title>
<meta name="layout" content="main-no-menu">
</head>

<body>
	<div class="span12">
		<legend>
			<g:message code="braid.courses.legend.students" />
		</legend>

		<g:if test="${!presenter.jedis}">
			<div class="alert alert-info">
				<p>
					<g:message code="braid.course.jedis.none.message" />
				</p>
			</div>
		</g:if>

		<g:if test="${presenter.students}">
			<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>DNI</th>
					<th>Nombre completo</th>
					<th>Usuario de Github</th>
					<th class="span4"><div class="pull-right">Acciones</div></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${presenter.students}" var="student">
					<tr>
						<td>
							${student.dni}
						</td>
						<td>
							${student.name}
						</td>
						<td>
							<g:each in="${student.authorities}" var="rol">
								<braid:roleLabel role="${rol.authority}"/>
							</g:each>
							<a href="http://www.github.com/${student.username}">
								${student.username}
							</a>
						</td>
						<td>
							<div class="pull-right">
								<g:link class="btn btn-small" controller="user" action="profile" params="[userId: student.id]">
									Ver perfil
								</g:link>
								<g:if test="${!presenter.jedis.contains(student)}">
									<g:link class="btn btn-small btn-success" controller="course" action="promote"
									params="[userId: student.id, courseId: presenter.course.id]">
										<i class="fa fa-arrow-circle-up"></i>
										Promover
									</g:link>
								</g:if>
								<g:else>
									<g:link class="btn btn-small btn-danger" controller="course" action="demote"
									params="[userId: student.id, courseId: presenter.course.id]">
										<i class="fa fa-arrow-circle-down"></i>
										Degradar
									</g:link>
								</g:else>
							</div>
						</td>
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
