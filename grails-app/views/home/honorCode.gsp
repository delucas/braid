<html>
<head>
	<title>código de honor</title>
	<meta name="layout" content="main">

	<parameter name="honorCode" value="active" />
	<r:require module="textEditor"/>
</head>
<body>

	<div class="span12">
		<legend> Código de Honor </legend>

		<g:if test="${flash.msg}">
			<div class="alert alert-info">
				${flash.msg}
			</div>
		</g:if>

		<sec:ifAnyGranted roles="ROLE_JEDI">
			<div class="well">
				<g:form controller="home" action="saveHonorCode">
					<div class="row-fluid">
						<g:textArea rows="7" class="textarea span12 wmd-panel" name="honorCode">${course.honorCode}</g:textArea>
					</div>

					<div class="row-fluid">
						<g:submitButton id="submitHonorCode" class="btn btn-small btn-primary pull-right" name="submit" value="Modificar Código de Honor"/>
					</div>
				</g:form>
			</div>
		</sec:ifAnyGranted>

		<sec:ifAnyGranted roles="ROLE_JAR_JAR, ROLE_PADAWAN">
			<div class="syllabus md">
				${course.honorCode}
			</div>
		</sec:ifAnyGranted>

	</div>

	<script type="text/javascript">
		$(function() {
			$('.textarea').wysihtml5({ image: false });
		});
	</script>

</body>
</html>