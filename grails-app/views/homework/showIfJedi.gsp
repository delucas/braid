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
		
		<div class="well">
		
			<legend>Consigna</legend>
			
			<div class="well">
				<markdown:renderHtml >${homework.wording}</markdown:renderHtml>
			</div>
			
			<button class="btn btn-large btn-block btn-primary" type="button" ${(homework.dueDate > new Date())?'disabled="disabled"':'' }>
				Corregir tareas de alumnos
			</button>
		</div>
		

</body>
</html>