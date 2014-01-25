<html>
<head>
	<title>trabajos prácticos</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			Nuevo trabajo práctico
		</legend>

		<braid:alertError bean="${command}"/>

		<div class="well span8">
			<g:form action="save" name="assignmentForm">

				<div class="row-fluid">
					<div class="control-group">
						<label class="control-label" for="inputTitle">Título</label>
						<div class="controls">
							<input type="text" id="inputTitle" name="title" value="${command?.title}"
							class="input-xlarge ${hasErrors(bean:command,field:'title', 'error')}"
							placeholder="Escriba un título para el trabajo práctico">
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="control-group ${hasErrors(bean:command,field:'startDate', 'error')}">
						<label class="control-label" for="inputStartDate">Fecha de inicio</label>
						<div class="controls datepicker">
							<g:datePicker name="startDate" precision="hour" years="[currentYear]"
							default="${command ? command.startDate : new Date() }"/>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="control-group ${hasErrors(bean:command,field:'dueDate', 'error')}">
						<label class="control-label" for="inputDueDate">Fecha de finalización</label>
						<div class="controls datepicker">
							<g:datePicker name="dueDate" precision="hour" years="[currentYear]"
							default="${command ? command.dueDate : new Date().plus(7) }"/>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="control-group">
						<label class="control-label" for="inputRepoName">Nombre del repositorio de Github</label>
						<div class="controls">
							<input type="text" id="inputRepoName" name="repoName" value="${command?.repoName}"
							class="${hasErrors(bean:command,field:'repoName', 'error')}" placeholder="ej. ecuaciones">
						</div>
					</div>
				</div>

			</g:form>
		</div>

		<div class="span8">
			<button id="submitAssignment" class="btn btn-small btn-primary pull-right">
				Crear trabajo práctico
			</button>
		</div>

	</div>

	<script>

		$(function(){

			$("#submitAssignment").on('click',function(){
				$('form[name="assignmentForm"]').submit()
			});
		});

	</script>

</body>
</html>