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
		
		<g:if test="${isTimeToGrade && !hasToGrade}">
			<braid:alertInfo title="¡Buen trabajo!">
				Ya se han corregido todas las tareas. Es momento de descansar...
			</braid:alertInfo>
		</g:if>
		
		<legend>
			${homework.title}
			<small class="pull-right">
				Fecha límite de entrega: <g:formatDate date="${homework.dueDate}" 
			  		format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<homework:wording homework="${homework}"/>
		
		<g:if test="${isTimeToGrade && hasToGrade}">
			<g:link action="grade" params="[homeworkId: homework.id]" class="btn btn-large btn-block btn-primary">
				Corregir tareas de alumnos
			</g:link>
		</g:if>
		
		<g:if test="${isTimeToGrade && !hasToGrade}">
			<table class="table table-striped table-hover">
				<thead>
					<th>Fecha de entrega</th>
					<th>Estudiante</th>
				</thead>
				<tbody>
				
					<g:each in="${solutions}" var="solution">
						<tr>
							<td width="50%">
								<g:formatDate date="${solution.dateCreated}" 
									format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/>
							</td>
							<td>${solution.user.name}</td>
						</tr>
						<tr class="hidden data">
							<td>
								<div class="well">
									<legend>Solución</legend>
									<markdown:renderHtml>${solution.text}</markdown:renderHtml>
								</div>
							</td>
							<td>
								<div class="well">
									<legend>Feedback</legend>
									<markdown:renderHtml>${solution.feedback}</markdown:renderHtml>
								</div>
							</td>
						</tr>
					</g:each>
				
				</tbody>
			</table>
		</g:if>
		
	</div>

	<script type="text/javascript">

		$(function(){
			$("tr").on('click', function(){
				$(".data").addClass('hidden')
				$(this).parent().children().removeClass('hidden')
			});
		});
	
	</script>

</body>
</html>