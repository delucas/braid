<html>

<head>
	<title>anuncios</title>
	
	<meta name="layout" content="main">
	<parameter name="announcements" value="active"/>
	
	<r:require module="markdown"/>
</head>

<body>

	<div class="span8">
		<legend> Anuncios </legend>
		
		<sec:ifAnyGranted roles="ROLE_YODA, ROLE_JEDI">
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
			$("#submitAnnouncement").on('click', function() {
				return window.confirm("¿Está seguro de que desea publicar ese anuncio?")
			});
		});

		function togglePreview(show) {
			if (show) {
				$("blockquote.preview").parent().removeClass('hidden')
				$("#submitAnnouncement").removeAttr('disabled');
			} else {
				$("blockquote.preview").parent().addClass('hidden')
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