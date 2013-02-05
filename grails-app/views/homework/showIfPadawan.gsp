<html>
<head>
	<title>tarea: ${homework.title}</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />
	
	<r:require modules="bootstrap-modal" />
	<r:require module="markdown"/>
</head>
<body>

	<div class="span12">
		
		<legend>
			${homework.title}
			
			<braid:statusHomework solved="${alreadySolved}"/>
			 
			<small class="pull-right">
				Fecha límite de entrega: <g:formatDate date="${homework.dueDate}" 
			  		format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>
		
		<g:elseif test="${alreadySolved && !homework.outOfDate}">
			<braid:alertInfo title="¡Atención! Ya contestaste esta pregunta">
				Si querés podés revisar tu respuesta y volver a remitirla dentro del período de vigencia. Es gratis ;)
			</braid:alertInfo>
		</g:elseif>
		
		<braid:alertError command="${command}"/>

		<homework:wording homework="${homework}"/>
				
		<div class="well">
			<legend>Mi respuesta</legend>
			<div id="previewArea" class="well preview md">
				<markdown:renderHtml>${command?.text}</markdown:renderHtml>
			</div>
			
		</div>
		
		<g:if test="${command?.feedback}">
		
			<div class="well">
				<legend>Feedback</legend>
				<div class="well md">
					<markdown:renderHtml>${command?.feedback}</markdown:renderHtml>
				</div>
				
			</div>
		
		</g:if>
		
		<g:if test="${!homework.outOfDate}">
			<div class="well">
				<g:form action="solve">
				
					<g:hiddenField name="homeworkId" value="${homework.id}"/>
					<g:hiddenField name="homeworkSolutionId" value="${command?.id}"/>
				
					<braid:textArea bean="${command}" beanField="text" name="text" id="solution"/>
					
					<div class="row-fluid">
						<span class="span6">
							<label class="checkbox ${hasErrors(bean:command,field:'honorCode', 'error')}" for="honorCode">
						      <input id="honorCode" type="checkbox" name="honorCode"> Declaro estar de acuerdo con el 
						      <a href="#honorCodeModal" data-toggle="modal">Código de Honor</a>
						    </label>
						</span>
						<span class="span6">
							<g:submitButton id="submitHomework" class="btn btn-small btn-primary pull-right" name="submit" value="Responder"/>
						</span>
					</div>
					
				</g:form>
				
			</div>
		</g:if>

	</div>

<div id="honorCodeModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Código de Honor</h3>
  </div>
  <div class="modal-body">
    <markdown:renderHtml>${course.honorCode}</markdown:renderHtml>
  </div>
</div>

	<script type="text/javascript">

		$(function() {
			$("#solution").on('keyup', function(){
				if ($(this).val() == '') {
					$("#submitHomework").attr('disabled','disabled')
				} else {
					$("#submitHomework").removeAttr('disabled')
				}
			});
		});

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