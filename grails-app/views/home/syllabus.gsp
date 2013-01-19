<html>
<head>

<title>plan de estudios</title>
<meta name="layout" content="main">

<parameter name="syllabus" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Plan de estudios </legend>

		<div class="syllabus md">
			<markdown:renderHtml>${course.syllabus}</markdown:renderHtml>
		</div>

	</div>

</body>
</html>