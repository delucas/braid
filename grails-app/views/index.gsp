<!DOCTYPE html>
<html>
<head>

<link rel="shortcut icon" href="${resource(dir:'images',file:'braid-logo.png')}" type="image/x-icon" />

<r:require modules="bootstrap" />
<r:layoutResources />

<style>
body {
	padding-top: 60px; /* 60px to make the container go all the way
      to the bottom of the topbar */
}
</style>

<title>braid · aprendiendo diferente</title>
</head>
<body>
	<div class="container">
		<div class="hero-unit">
			<div>
				<h1>braid <small>/breɪd/</small></h1>
				<p><strong>trenza.</strong> Conjunto de tres o más ramales que se entretejen, cruzándolos alternativamente.</p>
				<p>
				Asegurate de tener una cuenta en <a href="http://www.github.com">
						Github</a> para poder ingresar.
				</p>
			</div>
			<g:link class="btn btn-primary pull-right btn-large" 
				controller="auth" action="signin" params="[provider: 'github']">
				Ingresar a Braid 
			</g:link>
		</div>
		<hr>
		<div class="pull-right">© uno21 2011-<g:formatDate format="yyyy" date="${new Date()}"/></div>
	</div>
</body>
</html>
