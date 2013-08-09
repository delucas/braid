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
			${homework.title}: resultados
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

		<section class="revision">

			<div class="row-fluid">

				<div class="span6">
					<legend>
						Archivos
						<button class="btn pull-right" data-action="resize">
							<i class="icon icon-resize-full"></i>
						</button>
					</legend>
					<script src="https://gist.github.com/${solution.gist}.js">
						
					</script>
				</div>

				<div class="span6">
					<legend>Resultados de la revisión</legend>

					Comentarios de tus compañeros
					<g:each var="review" in="${reviews}" status="i">
						<blockquote>
							<markdown:renderHtml>${review.comments}</markdown:renderHtml>
							<small>Estudiante ${i+1}</small>
						</blockquote>
					</g:each>



<%--SACAR A UN TEMPLATE--%>



					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th></th>
								<g:each var="review" in="${reviews}" status="i">
									<th>E${i+1}</th>
								</g:each>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>¿El código es claro?</td>
								<g:each var="review" in="${reviews}" status="i">
									<th>${review.clarity}</th>
								</g:each>
							</tr>
							<tr>
								<td>¿El código respeta convenciones?</td>
								<g:each var="review" in="${reviews}" status="i">
									<th>${review.conventions}</th>
								</g:each>
							</tr>
							<tr>
								<td>¿El comportamiento es correcto?</td>
								<g:each var="review" in="${reviews}" status="i">
									<th>${review.correctness}</th>
								</g:each>
							</tr>
							<tr>
								<td>¿Las pruebas son buenas?</td>
								<g:each var="review" in="${reviews}" status="i">
									<th>${review.tests}</th>
								</g:each>
							</tr>
						</tbody>

					</table>

<%--FIN TEMPLATE--%>

					¿Qué es lo mejor de este trabajo?

					<g:each var="review" in="${reviews}" status="i">
						<blockquote>
							<markdown:renderHtml>${review.best}</markdown:renderHtml>
							<small>Estudiante ${i+1}</small>
						</blockquote>
					</g:each>

					¿Cómo lo mejoraría?

					<g:each var="review" in="${reviews}" status="i">
						<blockquote>
							<markdown:renderHtml>${review.advice}</markdown:renderHtml>
							<small>Estudiante ${i+1}</small>
						</blockquote>
					</g:each>

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