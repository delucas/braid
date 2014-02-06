
<html>
<%@page import="braid.course.Option"%>
<head>
<title>curso</title>
<meta name="layout" content="main">
<r:require module="colorPicker"/>
</head>
<body>

	<div class="span12">

		<legend> Configuración del curso </legend>

		<braid:alertError bean="${course}" />

		<g:form class="form-horizontal" action="config" method="post" name="courseForm">

			<div class="control-group ${hasErrors(bean: command, field: 'name', 'error')}">
				<label class="control-label" for="name">Nombre</label>
				<div class="controls">
					<input type="text" id="name" name="name"
						value="${command?.name}"
						class="span6"
						placeholder="e.g. Algoritmos y Programación 1">
				</div>
			</div>

			<div class="control-group ${hasErrors(bean: command, field: 'university', 'error')}">
				<label class="control-label" for="university">Universidad</label>
				<div class="controls">
					<input type="text" id="university" name="university"
						value="${command?.university}"
						class="span6"
						placeholder="e.g. Universidad Nacional de La Matanza">
				</div>
			</div>

			<div
				class="control-group ${hasErrors(bean: command, field: 'githubUsername', 'error')}">
				<label class="control-label" for="githubUsername">Usuario en Github</label>
				<div class="controls">
					<input type="text" id="githubUsername" name="githubUsername"
						value="${command?.settings.githubUsername}" class="span6"
						placeholder="e.g. ayp1">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="bannerColor">Color</label>
				<div class="controls">
					<input type="text" id="bannerColor" name="bannerColor"
						value="${command?.settings.bannerColor}"
						class="span6 minicolors"
						placeholder="#000000">
				</div>
			</div>

			<div class="control-group">
				<label class="control-label">Elementos en la plataforma</label>
				<div class="controls">
					<label for="homework">
						<g:checkBox id="homework" name="elements" value="1" checked="${course.settings.has(Option.homework)?'true':'false'}"/>
						Tareas
					</label>
					<label for="assignment">
						<g:checkBox id="assignment" name="elements" value="2" checked="${course.settings.has(Option.assignment)?'true':'false'}"/>
						Trabajos prácticos
					</label>
					<label for="codeReview">
						<g:checkBox id="codeReview" name="elements" value="4" checked="${course.settings.has(Option.codeReview)?'true':'false'}"/>
						Revisiones de código
					</label>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<button class="btn btn-primary">Guardar cambios</button>
				</div>
			</div>
		</g:form>
	</div>
	<script type="text/javascript">
		$(function(){
			$('.minicolors').minicolors();
		});
	</script>
</body>
</html>
