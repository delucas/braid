<html>
<head>
	<title>preguntas teóricas</title>
	<meta name="layout" content="main">
	<parameter name="questions" value="active" />
	
	<r:require module="markdown"/>
</head>
<body>

	<div class="span12">
		<legend>
			Nueva pregunta
		</legend>
		
		<div class="span8">
			<ul class="nav nav-pills">
				<li class="${(actionName=='list')?'active':''}"><g:link
						action="list">Listado</g:link></li>
				<li class="${(actionName=='random')?'active':''}"><g:link
						action="random">Pregunta aleatoria</g:link></li>
				<li class="${(actionName=='exam')?'active':''}"><g:link
						action="exam">Modelo de examen</g:link></li>
				<sec:ifAnyGranted roles="JEDI">
					<li class="pull-right ${(actionName=='create')?'active':''}"><g:link
							action="create">Nueva pregunta</g:link></li>
				</sec:ifAnyGranted>
			</ul>
		</div>

		<div class="well span8">
			<g:form action="save" name="questionForm">
		
				<div class="row-fluid">
					<select id="starsInput" name="level" class="input-small ${hasErrors(bean:command,field:'level', 'error')}">
						<option value="1" ${(command?.level == 1)?'selected="selected"':''}>&#9733;&#9734;&#9734;</option>
						<option value="2" ${(command?.level == 2)?'selected="selected"':''}>&#9733;&#9733;&#9734;</option>
						<option value="3" ${(command?.level == 3)?'selected="selected"':''}>&#9733;&#9733;&#9733;</option>
					</select>
					<input type="text" id="tagsInput" name="tags" class="input-xlarge pull-right ${hasErrors(bean:command,field:'tags', 'error')}"
					placeholder="tags, separados, por, comas" value="${command?.tags}"/>
				</div>
			
				<g:textArea rows="7" class="span12 wmd-panel ${hasErrors(bean:command,field:'wording', 'error')}" name="wording" id="question">${command?.wording}</g:textArea>
				<small class="pull-right">
					* Recuerde utilizar 
					<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
						formato markdown
					</a>
				</small>
				
			</g:form>
		</div>

		<div class="well span8 preview">
			<div class="row-fluid">
				<div class="span3">
					#${nextQuestionNumber} - 
					<span id="stars"><i class="icon-star"></i><i class="icon-star-empty"></i><i
						class="icon-star-empty"></i></span>
				</div>
				<div class="span9">
					<span id="tags" class="pull-right">
					</span>
				</div>
			</div>
			<div class="md" id="previewArea">
				<p>
					...
				</p>
			</div>
		</div>
		<div class="span8">
			<button id="submitQuestion" class="btn btn-small btn-primary pull-right">
				Crear pregunta
			</button>
		</div>

	</div>
	
	<script>

		function makeStars(black) {
			var white = 3 - black
			var result = ''
			
			for (var i=1;i<=black;i++) { 
				result += '<i class="icon-star"></i>'
			}
			for (var i=1;i<=white;i++) { 
				result += '<i class="icon-star-empty"></i>'
			}
			return result

		}

		function makeTags(tagArray) {
			var result = ''
			$.each(tagArray, function(index, value) {
				result += '<span class="label label-info">' + value + '</span>'
			});
			return result
		}


		function firstTime() {
			$("#stars").html(makeStars($("#starsInput").val()))

			var tags = $("#tagsInput").val().replace(/( )+/g,' ').replace(/,( )+/g,',').split(',')
			$("#tags").html(makeTags(tags))
			
		}

		function toggleSubmit(enable) {
			if (enable) {
				$("#submitQuestion").removeAttr('disabled');
			} else {
				$("#submitQuestion").attr('disabled','disabled');
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
				  toggleSubmit(true);
			    $preview.html(convert($textarea.val()));
			  } else {
				  toggleSubmit(false);
			}
		  }).trigger('keyup');
		});

		$(function(){

			firstTime();
			
			$("#starsInput").on('change',function(){
				$("#stars").html(makeStars($(this).val()))
			});
			$("#tagsInput").on('keyup',function(){
				var tags = $(this).val().replace(/( )+/g,' ').replace(/,( )+/g,',').split(',')
				$("#tags").html(makeTags(tags))
			});
			$("#submitQuestion").on('click',function(){
				$('form[name="questionForm"]').submit()
			});
		});
		
	</script>

</body>
</html>