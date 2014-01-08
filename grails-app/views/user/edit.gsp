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
				<braid:roleLabel role="${rol.authority}" />
			</g:each>
		</legend>

		<div class="row-fluid">
			<div class="span2">
				<img src="${user.avatarUrl}" class="pull-right profile" />
			</div>

			<div class="span10 well">

				<g:form class="form-horizontal" controller="user" action="save" id="${user.id}">

					<div class="control-group">
						<label class="control-label" for="name">Nombre completo</label>
						<div class="controls">
							<g:textField name="name" value="${user.name}"
								placeholder="Juan Pérez" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="dni">DNI</label>
						<div class="controls">
							<g:textField name="dni" value="${user.dni}"
								placeholder="35123456" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">Correo
							electrónico</label>
						<div class="controls">
							<g:textField name="email" value="${user.email}"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Cuenta en Github</label>
						<div class="controls">
							<i class="icon-share"></i> <a
								href="http://www.github.com/${user.username}">
								http://www.github.com/${user.username}
							</a>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">Guardar
								cambios</button>
						</div>
					</div>

				</g:form>

			</div>
		</div>

	</div>

</body>
</html>