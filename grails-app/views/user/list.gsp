<html>
<head>

<title>alumnos</title>
<meta name="layout" content="main">

<parameter name="students" value="active" />
</head>
<body>

	<div class="span12">
	
		<g:if test="${flash.message}">
			<div class="alert alert-info">
				<button type="button" class="close" data-dismiss="alert">×</button>
				${flash.message}
			</div>
		</g:if>
	
		<legend> Estudiantes para este curso </legend>

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
				<g:each in="${users}" var="user">
					<tr>
						<td>
							${user.dni}
						</td>
						<td>
							${user.name}
						</td>
						<td>
							<a href="http://www..github.com/${user.username}">
								${user.username}
							</a>
						</td>
						<td>
							<div class="pull-right">
								<g:link class="btn btn-small" controller="user" action="profile" params="[userId: user.id]">
									Ver perfil
								</g:link>
								<g:if test="${actionName=='pending'}">
									<g:link class="btn btn-small btn-success" controller="user" action="approve" params="[userId: user.id]">
										Aprobar
									</g:link>
									<g:link class="btn btn-small btn-danger" controller="user" action="reject" params="[userId: user.id]">
										Rechazar
									</g:link>
								</g:if>
							</div>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

	</div>

</body>
</html>