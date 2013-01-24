<html>
<head>

<title>tareas</title>
<meta name="layout" content="main">

<parameter name="homeworks" value="active" />
</head>
<body>

	<div class="span12">
		<legend>
			Tareas
		</legend>
		
		<div class="alert alert-info">
			<strong>Sobre las fechas de entrega</strong>
			<p>Recordá que las fechas de entrega son estrictas, y no se modifican por ninguna razón. Podrás
			mirar las consignas durante toda la cursada, pero sólo podrás responderlas antes de la fecha de
			entrega.</p>
		</div>
		
		<table class="table table-hover table-striped">
		  <thead>
		  	<tr>
			  	<th>Título</th>
			  	<th>Fecha de entrega</th>
			  	<th>&nbsp;</th>
		  	</tr>
		  </thead>
		  <tbody>
		  	<g:each in="${homeworks}" var="homework">
			  	<tr>
			  		<td>${homework.title}</td>
			  		<td>
			  			<g:formatDate date="${homework.dueDate}" 
			  			format="EEE, d MMM yyyy HH:mm z" timeZone="America/Argentina/Buenos_Aires"/>
			  		</td>
			  		<td>
			  			<g:link class="btn btn-small btn-primary" action="show" id="${homework.id}">
			  				Consigna
			  			</g:link>
			  		</td>
		  	</tr>
		  	</g:each>
		  </tbody>
		</table>

<%--		<g:each in="${questions}" var="question">--%>
<%--			<braid:question question="${question}"/>--%>
<%--		</g:each>--%>
<%--		--%>
<%--		<g:if test="${questionsTotal}">--%>
<%--			<div class="span8">--%>
<%--				<g:paginate class="pull-right" controller="question" action="list" total="${questionsTotal}" />--%>
<%--			</div>--%>
<%--		</g:if>--%>

	</div>

</body>
</html>