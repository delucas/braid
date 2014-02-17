<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>braid · <g:layoutTitle default="aprendiendo diferente" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir:'images',file:'braid-logo.png')}"
	type="image/x-icon" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">

<r:require module="common" />
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
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#"> <img class="logo"
					src="${resource(dir:'images',file:'braid-logo.png')}" alt="braid" />
					braid <small><g:meta name="app.version" /></small>
				</a>
				<div class="nav-collapse collapse pull-right">
					<ul class="nav">
						<li><g:link controller="logout">
								<i class="fa fa-power-off"></i>
					Salir
				</g:link></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid">

		<g:layoutBody />

	</div>

	<r:layoutResources />

</body>

</html>
