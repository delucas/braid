<html>
<head>
	<title>tareas</title>
	<meta name="layout" content="main">
	<parameter name="links" value="active" />

	<r:require module="textEditor"/>
</head>
<body>

	<div class="span12">
		<legend>
			Nuevo enlace
		</legend>

		<div class="well span8">
			<g:form action="save" name="linkForm">

					<div class="control-group ${hasErrors(bean:command, field:'caption', 'error')}">
						<label class="control-label" for="inputCaption">Etiqueta</label>
						<div class="controls">
							<input type="text" id="inputCaption" name="caption" value="${command?.caption}"
							class="${hasErrors(bean:command, field:'caption', 'error')}" placeholder="Escriba una etiqueta para el enlace">
						</div>
					</div>

					<div class="control-group ${hasErrors(bean:command, field:'url', 'error')}">
						<label class="control-label" for="inputURL">URL</label>
						<div class="controls">
							<input type="text" id="inputURL" name="url" value="${command?.url}"
							class="${hasErrors(bean:command, field:'url', 'error')}" placeholder="Escriba una URL para el enlace">
						</div>
					</div>

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