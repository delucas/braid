<html>
<head>
	<title>tarea: ${homework.title}</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />
	<r:require module="textEditor"/>
</head>
<body>

	<div class="span12">

		<legend>
			${homework.title}
			<small class="pull-right">
				<g:message code="braid.general.legend.dueDate"/>:
				<braid:dateInZone date="${homework.dueDate}"/>
			</small>
		</legend>

		<homework:wording homework="${homework}"/>

		<div class="row-fluid">
			<div class="span12">
				<homework:solution solution="${homeworkSolution}"/>
			</div>
		</div>

		<div class="well">
			<g:form elementId="gradeForm" action="gradeDo">

				<g:hiddenField name="homeworkSolutionId" value="${homeworkSolution.id}"/>

				<g:textArea rows="7" class="textarea span12 wmd-panel ${hasErrors(bean:command,field:'text', 'error')}"
					name="feedback" id="feedback">${command?.text}</g:textArea>

				<div class="row-fluid">
					<div class="span12">
						<g:hiddenField name="score"/>

						<div class="btn-group dropup pull-right">
							<button class="btn btn-primary btn-mini dropdown-toggle" data-toggle="dropdown">
								Dar feedback <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li>
									<a class="submitter" data-feedback="3">
										<i class="icon-star"></i>
										<i class="icon-star"></i>
										<i class="icon-star"></i>
										( 3 / 3 )
									</a>
								</li>
								<li>
									<a class="submitter" data-feedback="2">
										<i class="icon-star"></i>
										<i class="icon-star"></i>
										<i class="icon-star-empty"></i>
										( 2 / 3 )
									</a>
								</li>
								<li>
									<a class="submitter" data-feedback="1">
										<i class="icon-star"></i>
										<i class="icon-star-empty"></i>
										<i class="icon-star-empty"></i>
										( 1 / 3 )
									</a>
								</li>
							</ul>
						</div>

					</div>
				</div>

			</g:form>

		</div>

	</div>

	<script type="text/javascript">
		$(function() {
		  $(".submitter").click(function(){
			  $("#score").val($(this).attr('data-feedback'))
			  $(this).parents('form:first').submit()
		  });
		  $('.textarea').wysihtml5({ image: false });
		});
	</script>

</body>
</html>