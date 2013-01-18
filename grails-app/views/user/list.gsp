<html>
<head>

<title>usuarios</title>
<meta name="layout" content="main">

<parameter name="users" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Usuarios </legend>

		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>nombre completo</th>
					<th>username</th>
					<th class="span4">acciones</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${users}" var="user">
					<tr>
						<td>
							${user.name}
						</td>
						<td>
							${user.username}
						</td>
						<td>
							<g:link class="btn btn-small btn-primary" controller="user" action="profile" params="[userId: user.id]">
								Ver perfil
							</g:link>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>

	</div>

</body>
</html>