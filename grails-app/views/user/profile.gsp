<html>
<head>

<title>
	${user.name}
</title>
<meta name="layout" content="main">

<parameter name="users" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			${user.name}
		</legend>


		<div class="row-fluid">
			<div class="span3">
				<img src="http://lorempixel.com/150/150/people/" class="pull-right"/>
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
					<dt>Usuario de github</dt>
					<dd>
						<a href="http://www.github.com/${user.username}">
							${user.username}
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