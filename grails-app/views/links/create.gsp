<html>
<head>
	<title>tareas</title>
	<meta name="layout" content="main">
	<parameter name="links" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			Nuevo enlace
		</legend>

		<div class="well span8">
			<g:form action="save" name="linkForm">

					<g:render template="form" model="[link: link]"></g:render>

					<div class="control-group">
						<div class="controls">
							<button class="btn btn-small btn-primary">
								Crear enlace
							</button>
						</div>
					</div>

			</g:form>
		</div>

	</div>

</body>
</html>