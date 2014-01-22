<html>

<head>
	<title>anuncios</title>

	<meta name="layout" content="main">
	<parameter name="announcements" value="active"/>

	<r:require module="textEditor"/>
</head>

<body>

	<div class="span8">
		<legend> Anuncios </legend>

		<sec:ifAnyGranted roles="ROLE_YODA, ROLE_JEDI">
			<div class="well">
				<g:form controller="announcement" action="post">
					<div class="row-fluid">
						<g:textArea rows="7" class="textarea span12 wmd-panel" name="text" id="announcement"></g:textArea>
					</div>

					<div class="row-fluid">
						<g:submitButton id="submitAnnouncement" class="btn btn-small btn-primary pull-right" name="submit" value="Publicar anuncio"/>
					</div>
				</g:form>
			</div>
		</sec:ifAnyGranted>

		<div class="md hidden">
			<blockquote class="preview">
				<div id="previewArea">
				</div>
				<small>
					${sec.loggedInUserInfo(field:"username")}
					<span class="pull-right"><g:formatDate date="${new Date()}" timeZone="America/Argentina/Buenos_Aires"/></span>
				</small>
			</blockquote>
		</div>

		<g:each in="${announcements}" var="announcement">
			<announcement:render data="${announcement}"/>
		</g:each>

	</div>

	<aside class="span4 upcomingDates">
		<legend> Próximas fechas </legend>
		<g:each in="${upcomingDates}" var="event">
			<announcement:upcomingDate event="${event}"/>
		</g:each>
	</aside>

	<script type="text/javascript">
		$(function(){
			$('.textarea').wysihtml5({ image: false });

			$("#submitAnnouncement").on('click', function() {
				return window.confirm("¿Está seguro de que desea publicar ese anuncio?")
			});
		});
	</script>

</body>
</html>