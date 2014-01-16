<!DOCTYPE html>
<html>
<head>

<link rel="shortcut icon"
	href="${resource(dir:'images',file:'braid-logo.png')}"
	type="image/x-icon" />
<link href='http://fonts.googleapis.com/css?family=Shadows+Into+Light'
	rel='stylesheet' type='text/css'>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css"
	rel="stylesheet">
<link href="${resource(dir:'css',file:'landing.css')}" rel="stylesheet">

<r:require modules="bootstrap" />
<r:require modules="bootstrap-modal" />
<r:layoutResources />

<style>
</style>

<title>braid · aprendiendo diferente</title>
</head>
<body>

	<header>
		<div class="container">
			<div class="row-fluid header">
				<div class="span2">
					<div class="logo pull-right">
						<img src="${resource(dir:'images',file:'braid-big-logo.png')}" />
					</div>
				</div>
				<div class="span10">
					<span class="title">braid</span> <span class="tagline">aprendiendo
						diferente</span>

					<div class="buttons">
						<g:link class="btn btn-success pull-right btn-large"
							controller="auth" action="signin" params="[provider: 'github']">
							Ingresar a Braid
						</g:link>
					</div>
				</div>
			</div>
		</div>
	</header>

	<section class="hero">
		<div class="main row-fluid">

			<div class="container">
				<div class="row">
					<div class="well offset7 span5">
						<h1>
							braid <small>/breɪd/</small>
						</h1>
						<p>
							<strong>trenza.</strong> Conjunto de tres o más ramales que se
							entretejen, cruzándolos alternativamente.
						</p>

						<p>
							Asegurate de tener una cuenta en <a href="http://www.github.com">
								Github</a> para poder ingresar.
						</p>

						<hr />

						<h2>¿Te gustaría utilizar braid en tu curso?</h2>
						<p>
							Si sos <strong>docente</strong> de programación y te interesa
							implementar esta herramienta en tus cursos no dejes de
							contactarme. Es <em>gratis <a href="#gratis">*</a></em>.
						</p>

						<p>
							<a class="btn" href="#promoModal" data-toggle="modal">¿Cómo
								surge braid?</a> <a href="http://www.twitter.com/luke_ar"
								class="btn btn-primary"> <i class="icon-twitter"></i>
								@luke_ar
							</a>
						</p>

					</div>
				</div>
			</div>

		</div>
	</section>

	<hr />

	<section class="container">

		<div class="row">
			<div class="span4">
				<h2>Seguimiento</h2>
				<p>
					<strong>Braid</strong> permite hacer un seguimiento tan regular
					como quieras: se pueden asignar tareas y ver progresos durante toda
					la cursada. La corrección será tan simple como hacer dos clicks por
					alumno o tan compleja como redactar una devolución personalizada.
				</p>
			</div>
			<div class="span4">
				<h2>Automatización</h2>
				<p>
					Gracias al módulo <strong>grader</strong>, la herramienta permite
					la liberación de tareas de programación que pueden ser corregidas
					en forma automática por un servidor. Correctitud, estilo, buenas
					prácticas, desempeño&hellip; el límite lo ponés vos.
				</p>
			</div>
			<div class="span4">
				<h2>Intercambio</h2>
				<p>
					No puede escribirse buen código sin leer mucho código. Es por ello
					que uno de los modos de tarea es el de <strong>revisiones
						cruzadas</strong>, la que le da el nombre a la plataforma y permite que los
					estudiantes revisen el código de otros estudiantes, haciendo
					sugerencias y mejoras.
				</p>
			</div>
		</div>

	</section>

	<footer>
		<div class="container">

			<div class="row">
				<div class="span4" id="gratis">
					<p>* Esta herramienta tiene licencia MIT, por lo que podrás
						instalarla en tus servidores sin problemas y sin incurrir en
						costos.</p>
				</div>
				<div class="span4">
					<p>
						Sin embargo, como todo lo gratis vale lo que pagaste por ello, sí
						tendrás un cargo moral por el uso: <em>deberás ayudar a hacer
							crecer la plataforma</em>.
					</p>
					<p>
						Si aún así no te espanté, contactame <i class="icon-smile"></i>
					</p>
				</div>
				<div class="span4">
					<ul>
						<li><i class="icon-github"></i> <a
							href="http://www.github.com/delucas/braid"> Código fuente </a></li>
						<li><i class="icon-twitter"></i> <a
							href="http://www.twitter.com/luke_ar"> Contacto </a></li>
					</ul>
				</div>
			</div>

		</div>
	</footer>

	<div id="promoModal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3>braid @ StarTechConf 2013</h3>
		</div>
		<div class="modal-body">
			<iframe
				src="//player.vimeo.com/video/80576449?title=0&amp;byline=0&amp;portrait=0"
				width="526" height="300" frameborder="0" webkitallowfullscreen
				mozallowfullscreen allowfullscreen></iframe>
			<p>
				<strong>Protip:</strong> Recomendamos verlo en modo pantalla completa. 
			</p>
		</div>
	</div>

	<g:javascript library="application" />
	<r:layoutResources />
</body>
</html>
