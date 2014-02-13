<html>
<head>
	<title>tarea: ${presenter.homework.title}</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />

	<r:require module="textEditor"/>
</head>
<body>

	<div class="span12">

		<legend>
			${presenter.homework.title}

			<braid:statusHomework solved="${presenter.alreadySolved}"/>

			<small class="pull-right">
				Fecha límite de entrega:
				<braid:dateInZone date="${presenter.homework.dueDate}"/>
			</small>
		</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>

		<g:elseif test="${presenter.shouldAlertForAlreadySolved()}">
			<braid:alertInfo title="¡Atención! Ya contestaste esta pregunta">
				Si querés podés revisar tu respuesta y volver a remitirla dentro del período de vigencia. Es gratis ;)
			</braid:alertInfo>
		</g:elseif>

		<braid:alertError bean="${presenter.homeworkSolution}"/>

		<homework:wording homework="${presenter.homework}"/>

		<g:if test="${presenter.homework.outOfDate}">
			<div class="row-fluid">
				<div class="span12">
					<div class="well">
						<legend>Mi respuesta</legend>
						<div id="previewArea" class="well preview md">
							${presenter.homeworkSolution?.text}
						</div>

					</div>
				</div>
			</div>
		</g:if>

		<div class="row-fluid">
			<div class="span12">
				<homework:feedback solution="${presenter.homeworkSolution}"/>

				<g:if test="${presenter.canSolve()}">
					<div class="well">
						<g:form action="solve">

							<g:hiddenField name="homeworkId" value="${presenter.homework.id}"/>
							<g:hiddenField name="homeworkSolutionId" value="${presenter.homeworkSolution?.id}"/>

							<braid:textArea bean="${presenter.homeworkSolution}" beanField="text" name="text" id="solution"/>

							<div class="row-fluid">
								<span class="span6">
									<label class="checkbox ${hasErrors(bean:presenter.homeworkSolution, field:'honorCode', 'error')}" for="honorCode">
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
				<g:if test="${presenter.isJarJar()}">
					<div class="alert alert-info">
						<g:message code="braid.jarjar.onHold"/>
					</div>
				</g:if>
			</div>
		</div>


	</div>

<div id="honorCodeModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Código de Honor</h3>
  </div>
  <div class="modal-body">
    ${presenter.course.honorCode}
  </div>
</div>

	<script type="text/javascript">
		$(function() {
			$('.textarea').wysihtml5({ image: false });
		});
	</script>

</body>
</html>