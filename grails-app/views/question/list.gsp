<html>
<head>

<title>preguntas teóricas</title>
<meta name="layout" content="main">

<parameter name="questions" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Preguntas teóricas </legend>

		<ul>
			<g:each in="${questions}" var="question">
			<li>[${question.level}]<markdown:renderHtml>${question.wording}</markdown:renderHtml></li>
			</g:each>
		</ul>

	</div>

</body>
</html>