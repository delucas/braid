<html>
<head>

<meta name="layout" content="main-no-menu">

</head>
<body>

	<div class="row-fluid">
		<div class="span6">
			<div class="announcement">
				<h1>Bienvenido, humano</h1>

				<blockquote>
					<p>"For most of us the problem isn’t that we aim too high and
						fail - it’s just the opposite - we aim too low and succeed."</p>
					<p>
						<em>Sir Ken Robinson</em>, <a
							href="http://www.amazon.com/The-Element-Finding-Everything-ebook/dp/B002XHNMVM/ref=tmm_kin_title_0?ie=UTF8&qid=1358381827&sr=8-1">
							The Element </a>
					</p>
				</blockquote>

				<p>
					¡Hola, <strong>@${sec.loggedInUserInfo(field:"username")}</strong>!
				</p>
				<p>Sólamente te falta un paso para terminar con la registración
					en la plataforma.</p>
				<p>Por favor, completá los datos que te pedimos a continuación
					(los utilizaremos para habilitar tu perfil completo dentro del
					sistema).</p>
				<p>Muchas gracias,</p>

				<p>
					-- el equipo de <strong> braid </strong>
				</p>
			</div>
		</div>
		<div class="span6">
			<div>
				<legend> Datos personales </legend>
			</div>
			<form class="form-horizontal">
				<div class="control-group"></div>
				<div>
					<div class="control-group">
						<label class="control-label" for="nombre"> Nombre completo
						</label>
						<div class="controls">
							<input id="nombre" name="nombre" placeholder="Roberto C. Martin"
								type="text">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="dni"> DNI </label>
						<div class="controls">
							<input id="dni" name="dni" placeholder="35123456" type="text">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="curso"> Curso </label>
						<div class="controls">
							<select>
								<option value="1">Taller Web 1 - UNLaM</option>
								<option value="2">Taller Web 2 - UNLaM</option>
								<option value="3">Lenguaje de Programación 1 - UNTreF</option>
							</select>
						</div>
					</div>
					<div class="form-actions">
						<g:link class="btn btn-primary" action="announcements">¡Listo!</g:link>
					</div>
				</div>
			</form>
		</div>
	</div>


</body>
</html>