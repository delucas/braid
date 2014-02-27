<!DOCTYPE html>
<html>
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

<link rel="shortcut icon"
	href="${resource(dir:'images',file:'braid-logo.png')}"
	type="image/x-icon" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

<r:require module="common" />
<g:layoutHead />
<r:layoutResources />

<style>
body {
	padding-top: 46px;
}
</style>

</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#"> <img class="logo"
					src="${resource(dir:'images',file:'braid-logo.png')}" alt="braid" />
					braid <small><g:meta name="app.version" /></small>
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><g:link class="course-banner" controller="home"
								action="announcements"
								style="background-color: ${coursePresenter.bannerColor}">
								${coursePresenter.name}
							</g:link></li>
						<g:render template="/layouts/links"
							model="[links: coursePresenter.links]" />
					</ul>

					<ul class="nav pull-right navigation-menu">
						<li class="dropdown pull-right"><a href="#"
							class="dropdown-toggle bbnotgood" data-toggle="dropdown">Hola,
								@${sec.loggedInUserInfo(field:"username")} <b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><g:link controller="user" action="profile">
										<i class="fa fa-user"></i>
									Mi perfil
								</g:link></li>
								<sec:ifAllGranted roles="ROLE_JEDI">
									<li class="nav-header">Opciones de profesor</li>
									<li><g:link controller="course" action="config">
											<i class="fa fa-cogs"></i>
										Configurar curso
									</g:link></li>
									<li><g:link controller="user" action="pending">
											<i class="fa fa-users"></i>
										Solicitudes pendientes
									</g:link></li>
									<li class="nav-header">Cursos</li>
									<g:each in="${allCourses}" var="course">
										<li><g:link controller="course" action="select"
												id="${course.id}">
												<i class="fa fa-book"></i>
												${course.name}
											</g:link></li>
									</g:each>
								</sec:ifAllGranted>
								<sec:ifAllGranted roles="ROLE_YODA">
									<li class="nav-header">Opciones de administrador</li>
									<li><a href="javascript:alert('tbd')">Nombrar
											profesores</a></li>
									<li><a href="javascript:alert('tbd')">Crear cursos</a></li>
								</sec:ifAllGranted>
								<li class="divider"></li>
								<li><g:link controller="logout">
										<i class="fa fa-power-off"></i>
									Salir
								</g:link></li>
							</ul></li>
					</ul>
				</div>
				<!--/.nav-collapse -->

			</div>
		</div>
	</div>

	<div class="course-line"
		style="background-color: ${coursePresenter.bannerColor};"></div>

	<div class="container-fluid">
		<div class="row-fluid">

			<div class="tabbable tabs-left">

				<g:render template="/layouts/sidebar"
					model="[coursePresenter: coursePresenter]" />

				<div class="tab-content">
					<g:layoutBody />
				</div>

			</div>


		</div>
	</div>

	<g:javascript library="application" />
	<r:layoutResources />

	<script type="text/javascript">

	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-48434875-1']);
	  _gaq.push(['_trackPageview']);

	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();

	</script>

</body>
</html>
