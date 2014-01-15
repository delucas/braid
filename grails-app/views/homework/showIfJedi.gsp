<html>
<head>
	<title>tarea: ${presenter.homework.title}</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />
	
	<r:require modules="bootstrap-modal" />
</head>
<body>

	<div class="span12">
		
		<g:if test="${presenter.finishedGrading()}">
			<braid:alertInfo title="¡Buen trabajo!">
				Ya se han corregido todas las tareas. Es momento de descansar...
			</braid:alertInfo>
		</g:if>
		
		<legend>
			${presenter.homework.title}
			<small class="pull-right">
				Fecha límite de entrega:
				<g:formatDate date="${presenter.homework.dueDate}" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<homework:wording homework="${presenter.homework}"/>
		
		<g:if test="${presenter.hasToGrade()}">
			<g:link action="grade" params="[homeworkId: presenter.homework.id]" class="btn btn-large btn-block btn-primary">
				Corregir tareas de alumnos
			</g:link>
		</g:if>
		
		<g:if test="${presenter.finishedGrading()}">
			<table id="scores" class="table table-striped table-hover">
				<thead>
					<th>Fecha de entrega</th>
					<th>Estudiante</th>
				</thead>
				<tbody>
				
					<g:each in="${presenter.solutionsUpToDate}" var="solution">
						<tr class="score">
							<td width="50%">
								<g:formatDate date="${solution.dateCreated}" timeZone="America/Argentina/Buenos_Aires"/>
							</td>
							<td>${solution.user.name}</td>
						</tr>
						<tr class="hidden data">
							<td>
								<div class="well">
									<legend>Solución</legend>
										${solution.text}
								</div>
							</td>
							<td>
								<div class="well">
									<legend>Feedback <span class="badge">${solution.feedback?.score}</span></legend>
										${solution.feedback?.text}
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
			$("tr.score").on('click', function(){
				$(".data").addClass('hidden')
				$(this).parent().children().removeClass('hidden')
			});
		});
	
	</script>

</body>
</html>