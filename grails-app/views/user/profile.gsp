<html>
<head>

<title>
	${user.name}
</title>
<meta name="layout" content="main">

<parameter name="students" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			${user.name}
			<g:each in="${user.authorities}" var="rol">
				<braid:roleLabel role="${rol.authority}"/>
			</g:each>
		</legend>

		<div class="row-fluid">
			<div class="span2">
				<img src="${user.avatarUrl}" class="pull-right profile"/>
			</div>

			<div class="span10 well">
				<dl class="dl-horizontal">
					<dt>Nombre completo</dt>
					<dd>
						${user.name}
					</dd>
					<dt>DNI</dt>
					<dd>
						${user.dni}
					</dd>
					<dt>Correo electrónico</dt>
					<dd>
						${user.email}
					</dd>
				</dl>
				<dl class="dl-horizontal">
					<dt>Cuenta en Github</dt>
					<dd>
						<i class="icon-share"></i>
						<a href="http://www.github.com/${user.username}">
							http://www.github.com/${user.username}
						</a>
					</dd>
				</dl>

				<sec:ifAllGranted roles="ROLE_JEDI">
					<g:link class="btn pull-right" role="button" controller="user" action="edit" id="${user.id}">
						<i class="icon-pencil"></i>
						Editar perfil
					</g:link>
				</sec:ifAllGranted>

			</div>
		</div>

		<div class="row-fluid">
			<div class="span9">
				<legend>Progreso</legend>

				<g:render template="homework-solutions"></g:render>

				<g:render template="assignment-solutions"></g:render>

			</div>

			<div class="span3">
				<legend>Actividad</legend>

				<g:each in="${activity}" var="ac">
					<braid:activity type="${ac.type}" what="${ac.what}" when="${ac.when}"/>
				</g:each>

			</div>
		</div>

	</div>

</body>
</html>