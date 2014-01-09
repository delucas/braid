<html>
<head>
	<title>tareas</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />

	<r:require module="markdown"/>
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

				<g:textArea rows="7" class="span12 wmd-panel ${hasErrors(bean:command,field:'wording', 'error')}" name="wording" id="homework">${command?.wording}</g:textArea>
				<small class="pull-right">
					* Recuerde utilizar
					<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
						formato markdown
					</a>
				</small>

			</g:form>
		</div>

		<div class="well span8 preview">
			<div class="md" id="previewArea">
				<p>
					...
				</p>
			</div>
		</div>
		<div class="span8">
			<button id="submitHomework" class="btn btn-small btn-primary pull-right">
				Crear tarea
			</button>
		</div>

	</div>

	<script>

		function toggleSubmit(enable) {
			if (enable) {
				$("#submitHomework").removeAttr('disabled');
			} else {
				$("#submitHomework").attr('disabled','disabled');
			}
		}

		$(function() {
		  // When using more than one `textarea` on your page, change the following line to match the one you’re after
		  var $textarea = $('textarea'),
		      $preview = $('#previewArea'),
		      convert = new Markdown.getSanitizingConverter().makeHtml;

		  // instead of `keyup`, consider using `input` using this plugin: http://mathiasbynens.be/notes/oninput#comment-1
		  $textarea.keyup(function() {
			  if ($textarea.val()) {
				  toggleSubmit(true);
			    $preview.html(convert($textarea.val()));
			  } else {
				  toggleSubmit(false);
			}
		  }).trigger('keyup');
		});

		$(function(){

			$("#submitHomework").on('click',function(){
				$('form[name="homeworkForm"]').submit()
			});
		});

	</script>

</body>
</html>