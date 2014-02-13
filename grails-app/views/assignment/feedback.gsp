<html>
<head>
	<title>feedback: ${solution.assignment.title}</title>
	<meta name="layout" content="main">
	<parameter name="assignments" value="active" />
</head>

<body>

	<div class="span12">

		<legend>
			Feedback para "${solution.assignment.title}"
		</legend>

		<dl>

			<dt>Fecha de la solución</dt>
			<dd><braid:dateInZone date="${solution.dateCreated}"/></dd>

			<dt>Calificación</dt>
			<dd>${solution.score}/10.00</dd>

		</dl>

		<pre>${solution.feedback}</pre>

	</div>

</body>
</html>

