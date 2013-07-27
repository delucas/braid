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
			La orquesta: resultados
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
					<legend>Resultados de la revisión</legend>

					Comentarios de sus compañeros
					<blockquote>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante.</p>
						<small>Estudiante 1</small>
					</blockquote>
					<blockquote>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante.</p>
						<small>Estudiante 2</small>
					</blockquote>

					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th></th>
								<th>E1</th>
								<th>E2</th>
								<th>E3</th>
								<th>E4</th>
								<th>E5</th>
								<th>Yo</th>
								<th>Promedio</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>¿El código es claro?</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>2</td>
								<td>2.83</td>
							</tr>
							<tr>
								<td>¿El código respeta convenciones?</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>2</td>
								<td>2.83</td>
							</tr>
							<tr>
								<td>¿El comportamiento es correcto?</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>2</td>
								<td>2.83</td>
							</tr>
							<tr>
								<td>¿Las pruebas son buenas?</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>3</td>
								<td>2</td>
								<td>2.83</td>
							</tr>
						</tbody>

					</table>

					¿Qué es lo mejor de este trabajo?
					<blockquote>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante.</p>
						<small>Estudiante 1</small>
					</blockquote>
					<blockquote>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante.</p>
						<small>Estudiante 2</small>
					</blockquote>
					¿Cómo lo mejoraría?
					<blockquote>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante.</p>
						<small>Estudiante 1</small>
					</blockquote>
					<blockquote>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Integer posuere erat a ante.</p>
						<small>Estudiante 2</small>
					</blockquote>

				</div>

			</div>

		</section>

	</div>

</body>
</html>