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
				<span class="label label-info">${rol.authority}</span>
			</g:each>
		</legend>


		<div class="row-fluid">
			<div class="span3">
				<img src="${user.avatarUrl}" class="pull-right"/>
			</div>
	
			<div class="span9 well">
				<dl class="dl-horizontal">
					<dt>Nombre completo</dt>
					<dd>
						${user.name}
					</dd>
					<dt>DNI</dt>
					<dd>
						${user.dni}
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
			</div>
		</div>
		
		<div class="row-fluid">
			<div class="span9">
				<legend>Progreso</legend>
			</div>
	
			<div class="span3">
				<legend>Actividad</legend>
			</div>
		</div>

	</div>

</body>
</html>