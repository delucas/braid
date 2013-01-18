<html>
<head>

<title>preguntas teóricas</title>
<meta name="layout" content="main">

<parameter name="questions" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			Preguntas teóricas
		</legend>

			<ul class="nav nav-pills">
				<li class="${(actionName=='list')?'active':''}"><g:link
						action="list">Listado</g:link></li>
				<li class="${(actionName=='random')?'active':''}"><g:link
						action="random">Pregunta aleatoria</g:link></li>
				<li class="${(actionName=='exam')?'active':''}"><g:link
						action="exam">Modelo de examen</g:link></li>
			</ul>

		<g:each in="${questions}" var="question">
			<braid:question level="${question.level}">${question.wording}</braid:question>
		</g:each>

	</div>

</body>
</html>