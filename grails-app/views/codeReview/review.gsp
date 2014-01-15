<html>
<head>
<title><g:message code="braid.reviews.title.one"/>: ${homework.title}</title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />

<r:require modules="bootstrap-modal" />
</head>

<body>

	<div class="span12">

		<braid:alertError bean="${review}"/>

		<legend>
			${homework.title}: revisión cruzada
			<small class="pull-right">
			<g:message code="braid.general.legend.dueDate"/>: <g:formatDate
					date="${homework.reviewDueDate}" timeZone="America/Argentina/Buenos_Aires" />
			</small>
		</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>
		
		<g:if test="${solution.user == currentUser}">
			<braid:alertInfo title="¡Buen trabajo! Sólo queda una revisión">
				Ya revisaste el código de otras personas. Con el aprendizaje que eso
				te generó, revisá tu propio código. De esa manera podrás detectar
				muchas oportunidades de mejora.
			</braid:alertInfo>
		</g:if>

		<section class="md">

			<h2>Consigna</h2>
			${homework.wording}
		</section>

		<section class="revision">

			<div class="row-fluid">

				<div class="span6">
					<legend><g:message code="braid.reviews.CodeReview.files"/>
						<button class="btn pull-right" data-action="resize">
							<i class="icon icon-resize-full"></i>
						</button>
					</legend>
					<script src="https://gist.github.com/${solution.gist}.js">
						
					</script>
				</div>

				<div class="span6">
					<g:form action="review" id="${homework.id}">

						<g:hiddenField name="solutionId" value="${solution.id}" />

						<legend> Revisión </legend>

						<div
							class="control-group ${hasErrors(bean:review, field:'comments', 'error')}">

							<label class="control-label" for="comments"><g:message code="braid.reviews.CodeReview.comments"/>
								sobre el código</label>
							<div class="controls">

								<textarea name="comments" id="comments" class="span12"
									rows="7">${review?.comments}</textarea>

								<span class="help-inline"> <g:renderErrors
										bean="${review}" field="comments" />
								</span>
							</div>

						</div>

						<div class="control-group
						${hasErrors(bean:review, field:'clarity', 'error')} ${hasErrors(bean:review, field:'correctness', 'error')}
						${hasErrors(bean:review, field:'conventions', 'error')} ${hasErrors(bean:review, field:'tests', 'error')}">

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
										<tr class="${hasErrors(bean:review, field:'clarity', 'error')}">
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
											test="${hasErrors(bean:review, field:'clarity', 'error')}">
											<tr>
												<td colspan="5"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="clarity" />
												</span></td>
											</tr>
										</g:if>
										<tr
											class="${hasErrors(bean:review, field:'conventions', 'error')}">
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
											test="${hasErrors(bean:review, field:'conventions', 'error')}">
											<tr>
												<td colspan="5"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="conventions" />
												</span></td>
											</tr>
										</g:if>
										<tr
											class="${hasErrors(bean:review, field:'correctness', 'error')}">
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
												<td colspan="5"><span class="help-inline"> <g:renderErrors
															bean="${review}" field="correctness" />
												</span></td>
											</tr>
										</g:if>
										<tr class="${hasErrors(bean:review, field:'tests', 'error')}">
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
												<td colspan="5"><span class="help-inline"> <g:renderErrors
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

							<label class="control-label" for="commentsBest"> 
							<g:message code="braid.reviews.CodeReview.best"/> </label>
							<div class="controls">

								<textarea name="best" id="commentsBest" class="span12"
									rows="2">${review?.best}</textarea>

								<span class="help-inline"> <g:renderErrors
										bean="${review}" field="best" />
								</span>
							</div>

						</div>


						<div
							class="control-group ${hasErrors(bean:review,field:'advice', 'error')}">

							<label class="control-label" for="commentsAdvice">
								braid.reviews.CodeReview.advice </label>
							<div class="controls">

								<textarea name="advice" id="commentsAdvice" class="span12"
									rows="2">${review?.advice}</textarea>

								<span class="help-inline"> <g:renderErrors
										bean="${review}" field="advice" />
								</span>
							</div>

						</div>

						<div
							class="control-group">
							<div class="controls">
								<label
									class="checkbox ${hasErrors(bean:review, field:'honorCode', 'error')}"
									for="honorCode"> <input id="honorCode" type="checkbox"
										name="honorCode"> Declaro estar de acuerdo con el <a
										href="#honorCodeModal" data-toggle="modal">Código de Honor</a>
								</span>
							</div>
						</div>

						<g:submitButton name="Enviar revisión"
							class="btn btn-primary pull-right" />
					</g:form>
				</div>

			</div>

		</section>

	</div>

	<script>

		$(function(){
			$("button[data-action='resize']").on('click', function() {
				code = $(this).parent().parent()
				icon = $(this).find('i')

				code.toggleClass('span6')
				code.toggleClass('span12')
				icon.toggleClass('icon-resize')
				icon.toggleClass('icon-resize-small')
			})
		})
	
	</script>

</body>
</html>