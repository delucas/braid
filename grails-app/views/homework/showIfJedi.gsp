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
		
		<g:if test="${esMomentoDeCorregir && !hayQueCorregir}">
			<braid:alertInfo title="¡Buen trabajo!">
				Ya se han corregido todas las tareas. Es momento de descansar...
			</braid:alertInfo>
		</g:if>
		
		<legend>
			${homework.title}
			<small class="pull-right">
				Entrega: <g:formatDate date="${homework.dueDate}" 
			  		format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<homework:wording homework="${homework}"/>
		
		<g:if test="${esMomentoDeCorregir && hayQueCorregir}">
			<g:link action="grade" params="[homeworkId: homework.id]" class="btn btn-large btn-block btn-primary">
				Corregir tareas de alumnos
			</g:link>
		</g:if>

</body>
</html>