<html>
<head>

<title>anuncios</title>
<meta name="layout" content="main">

<parameter name="announcements" value="active"/>
</head>
<body>

	<div class="span8">
		<legend> Anuncios </legend>
		
		<sec:ifAnyGranted roles="YODA, JEDI">
			<div class="well">
				<g:form controller="announcement" action="post">
					<div class="row-fluid">
						<g:textArea class="span12" name="text"></g:textArea>
						<small>* Recuerde utilizar formato markdown</small>
					</div>
					<div class="row-fluid">
						<g:submitButton id="submitAnnouncement" class="btn btn-small btn-primary pull-right" name="submit" value="Publicar anuncio"/>
					</div>
				</g:form>
			</div>
		</sec:ifAnyGranted>

		<g:each in="${announcements}" var="announcement">
			<blockquote>
				<markdown:renderHtml>${announcement.text}</markdown:renderHtml>
				<small>${announcement.announcer.name} - ${announcement.dateCreated}</small>
			</blockquote>
		</g:each>

	</div>

	<div class="span4">
		<legend> Próximas fechas </legend>
	</div>

	<script type="text/javascript">
		$(function(){
			$("#submitAnnouncement").on('click', function() {
				return window.confirm("¿Está seguro de que desea publicar ese comentario?")
			});
		});

	</script>


</body>
</html>