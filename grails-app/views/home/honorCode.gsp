<html>
<head>

<title>código de honor</title>
<meta name="layout" content="main">

<parameter name="honorCode" value="active" />
</head>
<body>

	<div class="span12">
		<legend> Código de Honor </legend>

		<g:if test="${flash.msg}">
			<div class="alert alert-info">
				${flash.msg}
			</div>
		</g:if>

		<sec:ifAnyGranted roles="YODA, JEDI">
			<div class="well">
				<g:form controller="home" action="saveHonorCode">
					<div class="row-fluid">
						<g:textArea rows="7" class="span12 wmd-panel" name="honorCode">${course.honorCode}</g:textArea>
						<small class="pull-right">
							* Recuerde utilizar 
							<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
								formato markdown
							</a>
						</small>
					</div>
					
					<div class="row-fluid">
						<g:submitButton id="submitHonorCode" class="btn btn-small btn-primary pull-right" name="submit" value="Modificar Código de Honor"/>
					</div>
				</g:form>
			</div>
		</sec:ifAnyGranted>

		<sec:ifAnyGranted roles="JAR_JAR, PADAWAN">
			<div class="syllabus md">
				<markdown:renderHtml>${course.honorCode}</markdown:renderHtml>
			</div>
		</sec:ifAnyGranted>

	</div>

</body>
</html>