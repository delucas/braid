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

		<g:each in="${questions}" var="question">
			<braid:question question="${question}"/>
		</g:each>
		
		<div class="span8">
			<g:paginate class="pull-right" controller="question" action="list" total="${questionsTotal}" />
		</div>

	</div>

</body>
</html>