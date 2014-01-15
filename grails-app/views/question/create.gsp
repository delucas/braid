<html>
<head>
	<title>preguntas teóricas</title>
	<meta name="layout" content="main">
	<parameter name="questions" value="active" />

	<r:require module="textEditor"/>
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
<%--				<li class="${(actionName=='random')?'active':''}"><g:link--%>
<%--						action="random">Pregunta aleatoria</g:link></li>--%>
<%--				<li class="${(actionName=='exam')?'active':''}"><g:link--%>
<%--						action="exam">Modelo de examen</g:link></li>--%>
				<sec:ifAnyGranted roles="ROLE_JEDI">
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

				<g:textArea rows="7" class="textarea span12 wmd-panel ${hasErrors(bean:command,field:'wording', 'error')}" name="wording" id="question">${command?.wording}</g:textArea>

			</g:form>
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

		$(function(){

			$('.textarea').wysihtml5({ image: false });

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