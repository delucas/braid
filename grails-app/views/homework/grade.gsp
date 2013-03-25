<html>
<head>
	<title>tarea: ${homework.title}</title>
	<meta name="layout" content="main">
	<parameter name="homeworks" value="active" />
	
	<r:require modules="bootstrap-modal" />
	<r:require module="markdown"/>
</head>
<body>

	<div class="span12">
		
		<legend>
			${homework.title} 
			<small class="pull-right">
				Entrega:
				<g:formatDate date="${homework.dueDate}" timeZone="America/Argentina/Buenos_Aires"/>
			</small>
		</legend>
		
		<homework:wording homework="${homework}"/>
		
		<div class="row-fluid">
			<div class="span6">
				<homework:solution solution="${homeworkSolution}"/>
			</div>
			
			<div class="span6">
				<div class="well">
					<legend>Feedback</legend>
					<div id="previewArea" class="well preview md">
						<markdown:renderHtml></markdown:renderHtml>
					</div>
					
				</div>
			</div>
		</div>
		
		<div class="well">
			<g:form elementId="gradeForm" action="gradeDo">
			
				<g:hiddenField name="homeworkSolutionId" value="${homeworkSolution.id}"/>
			
				<g:textArea rows="7" class="span12 wmd-panel ${hasErrors(bean:command,field:'text', 'error')}" 
					name="feedback" id="feedback">${command?.text}</g:textArea>
				<small class="pull-right">
					* Recuerde utilizar 
					<a href="http://scottboms.com/downloads/documentation/markdown_cheatsheet.pdf" target="_blank">
						formato markdown
					</a>
				</small>
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
		  var $textarea = $('textarea'),
		      $preview = $('#previewArea'),
		      convert = new Markdown.getSanitizingConverter().makeHtml;

		  $textarea.keyup(function() {
			    $preview.html(convert($textarea.val()));
		  }).trigger('keyup');

		  $(".submitter").click(function(){
			  $("#score").val($(this).attr('data-feedback'))
			  $(this).parents('form:first').submit()
		  });
		  
		});
	</script>

</body>
</html>