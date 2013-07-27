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
			La orquesta: revisión cruzada
			<small class="pull-right">
			Entrega: <g:formatDate
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

		<section class="revision">

			<div class="row-fluid">

				<div class="span6">
					<legend>Archivos</legend>
					<script src="https://gist.github.com/3783899.js">
						
					</script>
				</div>

				<div class="span6">
					<g:form action="submitReview">

						<g:hiddenField name="codeReviewId" value="1" />
						<g:hiddenField name="solutionId" value="1" />

						<legend> Revisión </legend>

						<div
							class="control-group">

							<label class="control-label" for="comments">Comentarios
								sobre el código</label>
							<div class="controls">

								<textarea name="comments" id="comments" class="span12"
									rows="7">
									${review?.comments}
								</textarea>

<%--								<span class="help-inline"> <g:renderErrors--%>
<%--										bean="${review}" field="comments" />--%>
<%--								</span>--%>
							</div>

						</div>

						<div class="control-group error">

							<div class="controls">


								<table class="table table-striped table-hover">
									<thead>
										<tr class="smallFonts">
											<th></th>
											<th>No aplica</th>
											<th>Poco conforme</th>
											<th>Conforme</th>
											<th>Muy conforme</th>
										</tr>
									</thead>
									<tbody>
										<tr class="${hasErrors(bean:review,field:'clarity', 'error')}">
											<td>¿El código es claro?</td>
											<td><input id="denuncia" type="radio" name="clarity"
												value="0" ${(review?.clarity==0)?'checked':''} /></td>
											<td><input type="radio" name="clarity" value="1"
												${(review?.clarity==1)?'checked':''} /></td>
											<td><input type="radio" name="clarity" value="2"
												${(review?.clarity==2)?'checked':''} /></td>
											<td><input type="radio" name="clarity" value="3"
												${(review?.clarity==3)?'checked':''} /></td>
										</tr>
										<g:if
											test="${hasErrors(bean:review,field:'clarity', 'error')}">
											<tr>
												<td colspan="4"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="clarity" />
												</span></td>
											</tr>
										</g:if>
										<tr
											class="${hasErrors(bean:review,field:'conventions', 'error')}">
											<td>¿El código respeta convenciones?</td>
											<td><input id="denuncia" type="radio" name="conventions"
												value="0" ${(review?.conventions==0)?'checked':''} /></td>
											<td><input type="radio" name="conventions" value="1"
												${(review?.conventions==1)?'checked':''} /></td>
											<td><input type="radio" name="conventions" value="2"
												${(review?.conventions==2)?'checked':''} /></td>
											<td><input type="radio" name="conventions" value="3"
												${(review?.conventions==3)?'checked':''} /></td>
										</tr>
										<g:if
											test="${hasErrors(bean:review,field:'conventions', 'error')}">
											<tr>
												<td colspan="4"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="conventions" />
												</span></td>
											</tr>
										</g:if>
										<tr
											class="${hasErrors(bean:review,field:'correctness', 'error')}">
											<td>¿El comportamiento es correcto?</td>
											<td><input id="denuncia" type="radio" name="correctness"
												value="0" ${(review?.correctness==0)?'checked':''} /></td>
											<td><input type="radio" name="correctness" value="1"
												${(review?.correctness==1)?'checked':''} /></td>
											<td><input type="radio" name="correctness" value="2"
												${(review?.correctness==2)?'checked':''} /></td>
											<td><input type="radio" name="correctness" value="3"
												${(review?.correctness==3)?'checked':''} /></td>
										</tr>
										<g:if
											test="${hasErrors(bean:review,field:'correctness', 'error')}">
											<tr>
												<td colspan="4"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="correctness" />
												</span></td>
											</tr>
										</g:if>
										<tr class="${hasErrors(bean:review,field:'tests', 'error')}">
											<td>¿Las pruebas son buenas?</td>
											<td><input id="denuncia" type="radio" name="tests"
												value="0" ${(review?.tests==0)?'checked':''} /></td>
											<td><input type="radio" name="tests" value="1"
												${(review?.tests==1)?'checked':''} /></td>
											<td><input type="radio" name="tests" value="2"
												${(review?.tests==2)?'checked':''} /></td>
											<td><input type="radio" name="tests" value="3"
												${(review?.tests==3)?'checked':''} /></td>
										</tr>
										<g:if test="${hasErrors(bean:review,field:'tests', 'error')}">
											<tr>
												<td colspan="4"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="tests" />
												</span></td>
											</tr>
										</g:if>
									</tbody>

								</table>

							</div>
						</div>

						<div
							class="control-group ${hasErrors(bean:review,field:'best', 'error')}">

							<label class="control-label" for="commentsBest"> ¿Qué es
								lo mejor de este trabajo? </label>
							<div class="controls">

								<textarea name="best" id="commentsBest" class="span12"
									rows="2">
									${review?.best}
								</textarea>

								<span class="help-inline"> <g:renderErrors
										bean="${review}" field="best" />
								</span>
							</div>

						</div>


						<div
							class="control-group ${hasErrors(bean:review,field:'advice', 'error')}">

							<label class="control-label" for="commentsAdvice"> ¿Cómo
								lo mejoraría? </label>
							<div class="controls">

								<textarea name="advice" id="commentsAdvice" class="span12"
									rows="2">
									${review?.advice}
								</textarea>

								<span class="help-inline"> <g:renderErrors
										bean="${review}" field="advice" />
								</span>
							</div>

						</div>

						<div
							class="control-group ${hasErrors(bean:review,field:'honor', 'error')}">
							<div class="controls">
								<label class="checkbox"> <input type="checkbox"
									name="honor" value="true"> De acuerdo al Código de
									Honor, declaro que mis revisiones y sugerencias son objetivas y
									no tienen como fin desacreditar el trabajo de mis compañeros.
								</label> <span class="help-inline"> <g:renderErrors
										bean="${review}" field="honor" />
								</span>
							</div>
						</div>

						<g:submitButton name="Enviar revisión"
							class="btn btn-primary pull-right" />
					</g:form>
				</div>

			</div>

		</section>


		<script>
			function denunciar() {
				$("#comments").val('Denunciado por ');
				$("input#denuncia").attr('checked', 'checked');
				$("#commentsAdvice").val('No aplica');
				$("#commentsBest").val('No aplica');

				$("#comments").focus();
			}

			$(function() {
				$('.line').css('cursor', 'pointer');
				$('.line').click(
						function() {

							var linea = ($(this).attr('id').substring(2))
							var archivo = $(this).parent().parent().parent()
									.parent().children().last().children()
									.eq(1).html()

							var prefix = archivo + ':' + linea + " → "
							prefix = $('#comments').val() == '' ? prefix : '\n'
									+ prefix

							$('#comments').val($('#comments').val() + prefix)
							$('#comments').focus()
						});

			});
		</script>

	</div>

</body>
</html>