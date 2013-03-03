<%@page import="braid.Course"%>
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
			<g:form class="form-horizontal" controller="registerUser"
				action="finishRegistration">
				<div class="control-group"></div>
				<div>
				
					<div class="control-group ${hasErrors(bean:command,field:'name', 'error')}">
						<label class="control-label" for="username"> Username
							${user.username}
						</label>
						<g:hiddenField name="username"  value="${user?.username?:command.username}"/>
						<g:hiddenField name="oauthProvider"  value="${oauthProvider?:command.oauthProvider}"/>
						
						<div class="controls">
						
						</div>
					</div>
					<div class="control-group ${hasErrors(bean:command,field:'name', 'error')}">
						<label class="control-label" for="name"> Nombre completo
						</label>
						<div class="controls">
							<g:textField id="name" name="name"
								placeholder="Roberto C. Martin" type="text" 
								value="${command?.name}"/>
						</div>
						<span class="help-inline"><g:renderErrors bean="${command}" field="name" /></span>
					</div>
					
					<div class="control-group ${hasErrors(bean:command,field:'dni', 'error')}">
						<label class="control-label" for="dni"> DNI </label>
						<div class="controls">
							<g:textField id="dni" name="dni" placeholder="35123456"
								type="text" value="${command?.dni}"/>
						</div>
						<span class="help-inline"><g:renderErrors bean="${command}" field="dni" /></span>
					</div>
					
					<div class="control-group ${hasErrors(bean:command,field:'courseId', 'error')}">
						<label class="control-label" for="curso"> Curso </label>
						<div class="controls">
							<g:select id="courseId"
								name="courseId" value="${command?.courseId}"
							    noSelection="${['null':'Seleccione un curso']}"
							    from='${Course.list()}'
							    optionKey="id" optionValue="name"></g:select>

						</div>
						<span class="help-inline"><g:renderErrors bean="${command}" field="courseId" /></span>
					</div>
					
					<div class="form-actions">
						<g:submitButton class="btn btn-primary" value="¡Listo!" name="submit"/>
					</div>
					
				</div>
			</g:form>
		</div>
	</div>


</body>
</html>