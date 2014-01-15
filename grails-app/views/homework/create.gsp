<html>
<head>
	<title>tareas</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />

	<r:require module="textEditor"/>
</head>
<body>

	<div class="span12">
		<legend>
			Nueva tarea
		</legend>

		<braid:alertError bean="${command}"/>

		<div class="well span8">
			<g:form action="save" name="homeworkForm">

				<div class="row-fluid">
					<div class="control-group">
						<label class="control-label" for="inputTitle">Título</label>
						<div class="controls">
							<input type="text" id="inputTitle" name="title" value="${command?.title}"
							class="${hasErrors(bean:command,field:'title', 'error')}" placeholder="Escriba un título para la tarea">
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

				<g:textArea rows="7" class="textarea span12 ${hasErrors(bean:command,field:'wording', 'error')}" name="wording" id="homework">${command?.wording}</g:textArea>

			</g:form>
		</div>

		<div class="span8">
			<button id="submitHomework" class="btn btn-small btn-primary pull-right">
				Crear tarea
			</button>
		</div>

	</div>

	<script>
		$(function() {
			$('.textarea').wysihtml5({ image: false });

			$("#submitHomework").on('click',function(){
				$('form[name="homeworkForm"]').submit()
			});
		});
	</script>

</body>
</html>