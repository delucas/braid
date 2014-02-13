<html>
<head>
	<title>tarea: ${presenter.homework.title}</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />

</head>
<body>

	<div class="span12">

		<g:if test="${presenter.finishedGrading()}">
			<braid:alertInfo title="¡Buen trabajo!">
				Ya se han corregido todas las tareas. Es momento de descansar...
			</braid:alertInfo>
		</g:if>

		<g:if test="${!presenter.published}">
			<braid:alertInfo title="¡Es un secreto!">
				Aún no se ha dado a conocer esta tarea. Está planificado que se publique el
				<braid:dateInZone date="${presenter.homework.startDate}"/>
			</braid:alertInfo>
		</g:if>

		<legend>
			${presenter.homework.title}
			<small class="pull-right">
				Fecha límite de entrega:
				<braid:dateInZone date="${presenter.homework.dueDate}"/>
			</small>
		</legend>

		<homework:wording homework="${presenter.homework}"/>

		<g:if test="${presenter.hasToGrade()}">
			<g:link action="grade" params="[homeworkId: presenter.homework.id]" class="btn btn-large btn-block btn-primary">
				Corregir tareas de estudiantes
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
								<braid:dateInZone date="${solution.dateCreated}"/>
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