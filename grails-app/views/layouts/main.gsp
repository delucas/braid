<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>braid · <g:layoutTitle default="aprendiendo diferente" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${resource(dir:'images',file:'braid-logo.png')}" type="image/x-icon" />
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}"
	type="text/css">
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

<r:require modules="bootstrap" />
<g:layoutHead />
<r:layoutResources />

<style>
body {
	padding-top: 60px; /* 60px to make the container go all the way
      to the bottom of the topbar */
}
</style>

</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> 
				<a class="brand" href="#">
					<img class="logo" src="${resource(dir:'images',file:'braid-logo.png')}" alt="braid"/>
					braid <small>0.2</small>
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active">
							<g:link controller="home" action="announcements">Principal</g:link>
						</li>
						<li>
							<a href="https://groups.google.com/forum/?fromgroups#!forum/tallerwebjava">Grupo</a>
						</li>
						<li>
							<a href="https://github.com/tallerweb">Repo Github</a>
						</li>
						<li>
							<a href="https://github.com/tallerweb/notas/wiki">Wiki</a>
						</li>
						<li>
							<a href="https://docs.google.com/spreadsheet/viewform?formkey=dDdYNnIzRWFiV2FwbGtyNjA3a2NFZGc6MQ#gid=0">Feedback</a>
						</li>
					</ul>
					
					<ul class="nav pull-right">	
						<li class="dropdown pull-right">
	                        <a href="#" class="dropdown-toggle bbnotgood" data-toggle="dropdown">Hola, @${sec.loggedInUserInfo(field:"username")} <b class="caret"></b></a>
	                        <ul class="dropdown-menu">
	                          <li>
	                          	<g:link controller="user" action="profile">
	                          		Mi perfil
	                          	</g:link>
	                          </li>
	                          <sec:ifAllGranted roles="JEDI">
		                          <li class="nav-header">Opciones de profesor</li>
		                          <li>
		                          	<g:link controller="user" action="pending">
		                          		Solicitudes pendientes
		                          	</g:link>
		                          </li>
		                          <li><a href="#">Volcar notas</a></li>
	                          </sec:ifAllGranted>
	                          <sec:ifAllGranted roles="YODA">
		                          <li class="nav-header">Opciones de administrador</li>
		                          <li><a href="#">Nombrar profesores</a></li>
		                          <li><a href="#">Crear cursos</a></li>
		                      </sec:ifAllGranted>
	                          <li class="divider"></li>
	                          <li>
	                          	<g:link controller="logout">
	                          		Salir
	                          	</g:link>
	                          </li>
	                        </ul>
	                      </li>
					</ul>

<%--					<p class="navbar-text pull-right">--%>
<%--					--%>
<%--						<i class="icon-cog icon-white"></i> Hola, <a href="#"--%>
<%--							class="navbar-link">@username</a>--%>
<%--					</p>--%>
				</div>
				<!--/.nav-collapse -->

			</div>
		</div>
	</div>

	<div class="container">
		<div class="row-fluid">



			<div class="tabbable tabs-left">
				<ul class="nav nav-tabs span2">
					<li class="${pageProperty(name:'page.announcements')}">
						<g:link class="bbnotgood" controller="home" action="announcements">
							Anuncios
						</g:link>
					</li>
					<li class="${pageProperty(name:'page.syllabus')}">
						<g:link class="bbnotgood" controller="home" action="syllabus">
							Plan de estudios
						</g:link>
					</li>
					<li><a href="#" class="bbnotgood">Tareas</a></li>
					<li><a href="#" class="bbnotgood">Trabajos prácticos</a></li>
					<li class="${pageProperty(name:'page.questions')}">
						<g:link class="bbnotgood" controller="question" action="list">
							Preguntas teóricas
						</g:link>
					</li>
					<sec:ifAnyGranted roles="YODA, JEDI">
						<li class="${pageProperty(name:'page.students')}">
							<g:link class="bbnotgood" controller="user" action="list">
								Estudiantes
							</g:link>
						</li>
					</sec:ifAnyGranted>
					<li class="${pageProperty(name:'page.honorCode')}">
						<g:link class="bbnotgood" controller="home" action="honorCode">
							<strong>Código de Honor</strong>
						</g:link>
					</li>
				</ul>
				<div class="tab-content">
					<g:layoutBody />
				</div>
			</div>


		</div>

		<hr>
		<div class="pull-right">© uno21 2011-<g:formatDate format="yyyy" date="${new Date()}"/></div>
	</div>

	<g:javascript library="application" />
	<r:layoutResources />
</body>

</html>
