<html>
<head>
<title>revisión de código: la orquesta</title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />

<r:require modules="bootstrap-modal" />
</head>

<body>

	<div class="span12">

		<legend>
			La orquesta <small class="pull-right"> Entrega: <g:formatDate
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
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Mauris imperdiet nulla quis ante malesuada vehicula. Vestibulum
				fermentum adipiscing odio at pharetra. In ac arcu nisi. Sed
				dignissim hendrerit ipsum, molestie vestibulum dolor faucibus sed.
				Aenean porta at neque non dictum. Suspendisse a sapien nisl. Donec
				vehicula felis tortor. Vestibulum imperdiet est tempus purus
				accumsan, a lobortis lacus posuere. Maecenas vel vulputate nisl. In
				id magna et ipsum vehicula condimentum. Nam ultrices faucibus risus,
				euismod scelerisque quam euismod in. Phasellus venenatis mollis
				risus vitae porta.</p>

			<p>Maecenas at augue cursus, molestie purus eget, facilisis
				turpis. Integer ut elit erat. Quisque volutpat, augue eget tincidunt
				posuere, nisl sem volutpat augue, eget tincidunt massa leo ut
				libero. Donec a vehicula odio, vitae tempus nisl. Vestibulum sed
				ipsum volutpat nisi commodo ullamcorper. Morbi commodo vel urna in
				aliquet. Proin malesuada sapien vitae tortor ullamcorper sodales a
				at turpis. Cum sociis natoque penatibus et magnis dis parturient
				montes, nascetur ridiculus mus. In faucibus mi erat, a ultrices
				velit eleifend in. Proin ac pretium dui. Class aptent taciti
				sociosqu ad litora torquent per conubia nostra, per inceptos
				himenaeos.</p>
		</section>

		<section class="solve">
			<div class="well">

				<legend>Solución</legend>

				<g:form action="solve" method="post">
					<g:hiddenField name="codeReviewId" value="1" />

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
									class="span3" id="prependedInput" type="text"
									placeholder="5431625">
							</div>

							<label class="checkbox" for="honorCode"> <input
								id="honorCode" type="checkbox" name="honorCode"> Declaro
								estar de acuerdo con el <a href="#honorCodeModal"
								data-toggle="modal">Código de Honor</a>
							</label>

							<g:submitButton id="submitSolution"
								class="btn btn-primary btn-large pull-right" name="submit"
								value="Remitir solución" />
						</div>


					</div>
				</g:form>
			</div>
		</section>

	</div>

</body>
</html>