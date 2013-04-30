<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>braid · <g:layoutTitle default="aprendiendo diferente" /></title>
	
	<link rel="shortcut icon" href="${resource(dir:'images',file:'braid-logo.png')}" type="image/x-icon" />
	<link rel="stylesheet"
		href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
	
	<r:require module="common"/>
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
						<g:each in="${braid.Link.list([sort:'position'])}" var="link">
						<li>
							<a href="${link.url}">${link.caption}</a>
						</li>
						</g:each>
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
	                          <sec:ifAllGranted roles="ROLE_JEDI">
		                          <li class="nav-header">Opciones de profesor</li>
		                          <li>
		                          	<g:link controller="user" action="pending">
		                          		Solicitudes pendientes
		                          	</g:link>
		                          </li>
	                          </sec:ifAllGranted>
	                          <sec:ifAllGranted roles="ROLE_YODA">
		                          <li class="nav-header">Opciones de administrador</li>
		                          <li><a href="javascript:alert('tbd')">Nombrar profesores</a></li>
		                          <li><a href="javascript:alert('tbd')">Crear cursos</a></li>
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
				</div>
				<!--/.nav-collapse -->

			</div>
		</div>
	</div>

	<div class="container">
		<div class="row-fluid">



			<div class="tabbable tabs-left">
				<ul class="nav nav-tabs span2">
					<sec:ifAllGranted roles="ROLE_JEDI">
						<li class="${pageProperty(name:'page.dashboard')}">
							<g:link class="bbnotgood" controller="home" action="dashboard">
								<strong>Dashboard</strong>
							</g:link>
						</li>
					</sec:ifAllGranted>
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
					<li class="${pageProperty(name:'page.questions')}">
						<g:link class="bbnotgood" controller="question" action="list">
							Preguntas teóricas
						</g:link>
					</li>
					<li class="${pageProperty(name:'page.homeworks')}">
						<g:link class="bbnotgood" controller="homework" action="list">
							Tareas
						</g:link>
					</li>
					<li class="${pageProperty(name:'page.assignments')}">
						<g:link class="bbnotgood" controller="assignment" action="list">
							Trabajos prácticos
						</g:link>
					</li>
					
					<li class="${pageProperty(name:'page.exams')}">
						<g:link class="bbnotgood" controller="exam" action="list">
							Evaluaciones
						</g:link>
					</li>
					
					<sec:ifAnyGranted roles="ROLE_YODA, ROLE_JEDI">
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
