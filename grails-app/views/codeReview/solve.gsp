<html>
<head>
<title>revisión de código: ${homework.title}</title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />

<r:require modules="bootstrap-modal" />
</head>

<body>

	<div class="span12">

		<legend>
			${homework.title} <small class="pull-right"> Entrega: <g:formatDate
					date="${new Date() + 4}" timeZone="America/Argentina/Buenos_Aires" />
			</small>
		</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>

		<section class="md">

			<h2>Consigna</h2>
			<markdown:renderHtml>${homework.wording}</markdown:renderHtml>
		</section>

		<section class="solve">
			<div class="well">

				<legend>Solución</legend>

				<g:form action="solve" id="${homework.id}" method="post">
					<g:hiddenField name="solutionId" value="${previousSolution?.id?:null}"/>

					<div class="row-fluid">

						<div class="span6">
							<div class="alert alert-info">
								<strong>Sobre la forma de entrega</strong>
								<p>
									Dado que para utilizar <strong>braid</strong> ingresaste con tu
									cuenta de <a href="http://www.github.com">Github</a>,
									utilizaremos como convención que desde allí entregarás tus
									trabajos prácticos.
								</p>

								<p>
									Deberás crear un <a href="http://gist.github.com">Gist</a> para
									entregar esta revisión.
								</p>

								<p>
									Una vez que lo hayas hecho, debés copiar en el campo del
									formulario la dirección de ese Gist, ignorando el dominio y
									dejando <b>únicamente el número que identifica tu trabajo</b>.
									Validaremos ese campo, pero puedes hacerlo bien en el primer
									intento.
								</p>
							</div>
						</div>

						<div class="span6">

							<div class="input-prepend">
								<span class="add-on">http://gist.github.com/delucas/</span> <input
									class="span3 ${hasErrors(bean:previousSolution, field:'gist', 'error')}" id="prependedInput" type="text"
									placeholder="5431625" name="gist" value="${previousSolution?.gist}"/>
							</div>

							<label class="checkbox ${hasErrors(bean:previousSolution, field:'honorCode', 'error')}" for="honorCode"> <input
								id="honorCode" type="checkbox" name="honorCode"> Declaro
								estar de acuerdo con el <a href="#honorCodeModal"
								data-toggle="modal">Código de Honor</a>
							</label>

							<button type="submit" class="btn btn-primary btn-large pull-right">
								Remitir solución
							</button>
						</div>


					</div>
				</g:form>
			</div>
		</section>

	</div>

</body>
</html>