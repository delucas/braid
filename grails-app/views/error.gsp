<!DOCTYPE html>
<html>
<head>
<title>Oops!</title>

<r:require modules="bootstrap" />
<r:layoutResources />

<style>
body {
	padding-top: 60px; /* 60px to make the container go all the way
      to the bottom of the topbar */
}

.center {
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	margin-bottom: auto;
	margin-top: auto;
}
</style>

</head>
<body>

	<div class="hero-unit center">
		<h1>
			¡Oops! <small>algo no resultó como se esperaba</small>
		</h1>
		<br />
		<p>Nuestros servidores no pudieron responder a tu pedido.</p>
		<p>Intentá nuevamente más tarde.</p>
		<p>O podrías presionar este lindo botón:</p>
		<g:link controller="home" action="announcements"
			class="btn btn-large btn-info">
			<i class="icon-home icon-white"></i> Llevame al inicio
		</g:link>
	</div>


</body>
</html>
