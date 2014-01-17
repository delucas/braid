<html>
<head>
<title>trabajo práctico: ${presenter.assignment.title}</title>
<meta name="layout" content="main">
<parameter name="assignments" value="active" />

<r:require modules="bootstrap-modal" />
</head>

<body>

	<div class="span12">

		<g:if test="${!presenter.published}">
			<braid:alertInfo title="¡Es un secreto!">
				Aún no se ha dado a conocer este trabajo práctico. Está planificado que se publique el
				<g:formatDate date="${presenter.assignment.startDate}" timeZone="America/Argentina/Buenos_Aires"/>
			</braid:alertInfo>
		</g:if>

		<legend>
			${presenter.assignment.title}
			<small class="pull-right"> <g:message code="braid.general.legend.dueDate"/>: <g:formatDate
					date="${presenter.assignment.dueDate}"
					timeZone="America/Argentina/Buenos_Aires" />
			</small>
		</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>

		<braid:alertError bean="${presenter.command}"/>

		<assignment:wording assignment="${presenter.assignment}"/>
		
		<g:if test="${!presenter.canSubmit()}">
			<braid:alertInfo title="Info">
				<p>Por favor, espera que graduemos tu última respuesta para poder
				remitir una nueva. En caso de error, podrás reintentar en unos minutos.</p>
				<p>Tu rol para participar en esta actividad debe ser <span class="label label-info">PADAWAN</span>.
				En caso contrario deberás esperar por la aprobación de un docente.</p>
			</braid:alertInfo>
		</g:if>
		
		<g:if test="${!presenter.assignment.outOfDate && presenter.canSubmit()}">
			<section class="solve">
				<div class="well">
				
					<legend>Preparación</legend>
					
					<p>Recordá que antes de empezar a trabajar necesitás 
					<a href="${resource(dir:'images',file:'fork.gif')}" target="_blank">hacer un fork</a> 
					del repositorio original. Una vez que lo hagas, deberás <b>clonar</b> tu
					copia en el workspace y comenzar la resolución. Ejemplo:</p>
					
<pre>
${sec.loggedInUserInfo(field:"username")}@computadora:~/workspace$ git clone git@github.com:${sec.loggedInUserInfo(field:"username")}/${presenter.assignment.repo.name}.git
Cloning into '${presenter.assignment.repo.name}'...
remote: Counting objects: 39, done.
remote: Compressing objects: 100% (27/27), done.
remote: Total 39 (delta 5), reused 38 (delta 4)
Receiving objects: 100% (39/39), 8.38 KiB, done.
Resolving deltas: 100% (5/5), done.
${sec.loggedInUserInfo(field:"username")}@computadora:~/workspace$ cd ${presenter.assignment.repo.name}/
${sec.loggedInUserInfo(field:"username")}@computadora:~/workspace/${presenter.assignment.repo.name}$
</pre>
					
					<p>Una vez termines, tendrás que hacer los <code>commit</code> y 
					<code>push</code> correspondientes</p>
					
					<a href="${presenter.assignment.repo.url}" target="_blank"
					class="btn btn-large btn-primary pull-right">
						Ir al repositorio original<br><small>(recordá realizar un fork)</small>
					</a>
					
					<div class="clearfix"></div>
				
					<legend>Solución</legend>
					
					<g:form action="solve">
						<g:hiddenField name="assignmentId"
							value="${presenter.assignment.id}" />
	
						<div class="alert alert-info">
							<strong>Sobre la forma de entrega</strong>
							<p>Dado que para utilizar <strong>braid</strong> ingresaste con tu
							cuenta de <a href="http://www.github.com">Github</a>, utilizaremos
							como convención que desde allí entregarás tus trabajos prácticos.</p>
							<p>Es por ello que al oprimir el botón <em>Remitir solución</em>,
							asumiremos que ya hiciste <code>commit</code> y <code>push</code> de los
							cambios que hacen a tu respuesta. Las entregas no válidas <strong>no se
							calificarán y serán anuladas</strong>.</p>
						</div>
	
						<div class="row-fluid">
								<span class="span6"> <label
									class="checkbox ${hasErrors(bean:presenter.command,field:'honorCode', 'error')}"
									for="honorCode"> <input id="honorCode" type="checkbox"
										name="honorCode"> Declaro estar de acuerdo con el <a
										href="#honorCodeModal" data-toggle="modal">Código de Honor</a>
								</label>
								</span> <span class="span6"> <g:submitButton id="submitHomework"
										class="btn btn-large btn-primary pull-right" name="submit"
										value="Remitir solución" />
								</span>
						</div>
	
					</g:form>
				</div>
			</section>
		</g:if>
		
		<section class="entregas">

				<legend>Historial de entregas</legend>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Fecha de la solución</th>
							<th>Calificación</th>
							<th>Detalles</th>
						</tr>
					</thead>
					<tbody>
						<g:if test="${presenter.solutions}">
							<g:each in="${presenter.solutions}" var="solution">
								<tr>
									<td><g:formatDate date="${solution.dateCreated}"
											timeZone="America/Argentina/Buenos_Aires" /></td>
									<td>
										<g:if test="${solution.graded}">
											${solution.score}/10.00
										</g:if>
										<g:else>
											Esperando graduación
										</g:else>
									</td>
									<td>
										<g:if test="${solution.graded}">
											<g:link action="feedback" id="${solution.id}"
												role="button" class="btn">Ver detalles</g:link>
										</g:if>
									</td>
								</tr>
							</g:each>
							<tfoot>
								<tr>
									<td>Nota actual</td>
									<td>
										${presenter.bestSolution.score}/10.00
									</td>
									<td><g:link action="feedback" id="${presenter.bestSolution.id}"
											role="button" class="btn">Ver detalles</g:link></td>
								</tr>
							</tfoot>
						</g:if>
						<g:else>
							<tr>
								<td>No se han remitido soluciones aún</td>
								<td>-/10.00</td>
								<td>No disponible</td>
							</tr>
						</g:else>
					</tbody>
				</table>

			</section>

	</div>

	<g:render template="/home/honorCodeModal" model="[course: presenter.course]" />

</body>
</html>