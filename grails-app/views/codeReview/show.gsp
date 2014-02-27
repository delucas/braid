<html>
<head>
<title><g:message code="braid.reviews.title.one"/>: ${homework.title}</title>
<meta name="layout" content="main">

<parameter name="codeReview" value="active" />
</head>

<body>

	<div class="span12">

		<legend>
			${homework.title} <small class="pull-right"> <g:message code="braid.general.legend.dueDate"/>:
			<braid:dateInZone date="${homework.solutionDueDate}"/>
			</small>
		</legend>

		<g:if test="${flash.message}">
			<braid:alertInfo title="Info">
				${flash.message}
			</braid:alertInfo>
		</g:if>

		<section class="md">

			<h2>Consigna</h2>
			${homework.wording}
		</section>

	</div>

</body>
</html>