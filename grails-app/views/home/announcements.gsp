<html>
<head>

<title>anuncios</title>
<meta name="layout" content="main">
<g:javascript src="Markdown.Converter.js" />
<g:javascript src="Markdown.Sanitizer.js" />

<parameter name="announcements" value="active"/>
</head>
<body>

	<div class="span8">
		<legend> Anuncios </legend>
		
		<sec:ifAnyGranted roles="YODA, JEDI">
			<div class="well">
				<g:form controller="announcement" action="post">
					<div class="row-fluid">
						<g:textArea rows="7" class="span12 wmd-panel" name="text" id="announcement"></g:textArea>
						<small class="pull-right">
							* Recuerde utilizar 
							<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
								formato markdown
							</a>
						</small>
					</div>
					
					<div class="preview md hidden">
						<blockquote>
							<div id="previewArea">
							</div>
							<small>
								${sec.loggedInUserInfo(field:"username")} 
								<span class="pull-right"><g:formatDate date="${new Date()}" format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/></span>
							</small>
						</blockquote>
					</div>
					
					<div class="row-fluid">
						<g:submitButton id="submitAnnouncement" class="btn btn-small btn-primary pull-right" name="submit" value="Publicar anuncio"/>
					</div>
				</g:form>
			</div>
		</sec:ifAnyGranted>

		<g:each in="${announcements}" var="announcement">
			<blockquote class="md">
				<markdown:renderHtml>${announcement.text}</markdown:renderHtml>
				<small>
					${announcement.announcer.name} 
					<span class="pull-right"><g:formatDate date="${announcement.dateCreated}" format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/></span>
				</small>
			</blockquote>
		</g:each>

	</div>

	<div class="span4">
		<legend> Próximas fechas </legend>
	</div>

	<script type="text/javascript">
		$(function(){
			$("#submitAnnouncement").on('click', function() {
				return window.confirm("¿Está seguro de que desea publicar ese anuncio?")
			});
		});

		function togglePreview(show) {
			if (show) {
				$("div.preview").removeClass('hidden')
				$("#submitAnnouncement").removeAttr('disabled');
			} else {
				$("div.preview").addClass('hidden')
				$("#submitAnnouncement").attr('disabled','disabled');
			}
		}

		$(function() {
			  // When using more than one `textarea` on your page, change the following line to match the one you’re after
			  var $textarea = $('textarea'),
			      $preview = $('#previewArea'),
			      convert = new Markdown.getSanitizingConverter().makeHtml;

			  // instead of `keyup`, consider using `input` using this plugin: http://mathiasbynens.be/notes/oninput#comment-1
			  $textarea.keyup(function() {
				  if ($textarea.val()) {
					  togglePreview(true);
				    $preview.html(convert($textarea.val()));
				  } else {
					  togglePreview(false);
				}
			  }).trigger('keyup');
			});
		

	</script>


</body>
</html>