<html>
<head>

<title>tarea: ${homework.title}</title>
<meta name="layout" content="main">
<r:require modules="bootstrap-modal" />
<g:javascript src="Markdown.Converter.js" />
<g:javascript src="Markdown.Sanitizer.js" />

<parameter name="homeworks" value="active" />
</head>
<body>

	<div class="span12">
		
		<legend>
			${homework.title} 
			<small class="pull-right">
				Entrega: <g:formatDate date="${homework.dueDate}" 
			  		format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<homework:wording homework="${homework}"/>
		
		<div class="well">
			<legend>Respuesta de ${homeworkSolution.user.name}</legend>
			<div class="well md">
				<markdown:renderHtml>${homeworkSolution.text}</markdown:renderHtml>
			</div>
		</div>
		
		<div class="well">
			<legend>Feedback</legend>
			<div id="previewArea" class="well preview md">
				<markdown:renderHtml></markdown:renderHtml>
			</div>
			
		</div>
		
		<div class="well">
			<g:form action="gradeDo">
			
				<g:hiddenField name="homeworkSolutionId" value="${homeworkSolution.id}"/>
			
				<g:textArea rows="7" class="span12 wmd-panel ${hasErrors(bean:command,field:'text', 'error')}" 
					name="feedback" id="feedback">${command?.text}</g:textArea>
				<small class="pull-right">
					* Recuerde utilizar 
					<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
						formato markdown
					</a>
				</small>
				<div class="row-fluid">
					<div class="span12">
						<g:submitButton id="submitFeedback" class="btn btn-small btn-primary pull-right" name="submit" value="Dar feedback"/>
					</div>
				</div>
				
			</g:form>
			
		</div>
		
	</div>
	
	<script type="text/javascript">
		$(function() {
		  var $textarea = $('textarea'),
		      $preview = $('#previewArea'),
		      convert = new Markdown.getSanitizingConverter().makeHtml;

		  $textarea.keyup(function() {
			    $preview.html(convert($textarea.val()));
		  }).trigger('keyup');

		});
	</script>

</body>
</html>