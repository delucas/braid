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
			<small class="pull-right">
				Entrega: <g:formatDate date="${homework.dueDate}" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<div class="well">
		
			<legend>Consigna</legend>
			
			<div class="well">
				<markdown:renderHtml >${homework.wording}</markdown:renderHtml>
			</div>
			
			<legend>Mi respuesta</legend>
			<div id="previewArea" class="well preview md">-</div>
			
		</div>
		
		<g:if test="${!homework.outOfDate}">
			<div class="well">
				<g:form action="solve">
				
					<g:hiddenField name="homeworkId" value="${homework.id}"/>
					<g:hiddenField name="homeworkSolutionId" value="${command?.homeworkSolutionId}"/>
				
					<g:textArea rows="7" class="span12 wmd-panel ${hasErrors(bean:command, field:'text', 'error')}" 
						name="text" id="solution">${command?.text}</g:textArea>
					<small class="pull-right">
						* Recuerde utilizar 
						<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
							formato markdown
						</a>
					</small>
					<div class="row-fluid">
						<span class="span6">
							<label class="checkbox ${hasErrors(bean:command, field:'honorCode', 'error')}" for="honorCode">
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

	<g:render template="/home/honorCodeModal" model="[course: course]"/>

</body>
</html>