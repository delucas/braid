<div id="honorCodeModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Código de Honor</h3>
  </div>
  <div class="modal-body">
    <markdown:renderHtml>${course.honorCode}</markdown:renderHtml>
  </div>
</div>

<script type="text/javascript">

	$(function() {
		$("#solution").on('keyup', function(){
			if ($(this).val() == '') {
				$("#submitHomework").attr('disabled','disabled')
			} else {
				$("#submitHomework").removeAttr('disabled')
			}
		});
	});

	$(function() {
	  // When using more than one `textarea` on your page, change the following line to match the one you’re after
	  var $textarea = $('textarea'),
	      $preview = $('#previewArea'),
	      convert = new Markdown.getSanitizingConverter().makeHtml;

	  // instead of `keyup`, consider using `input` using this plugin: http://mathiasbynens.be/notes/oninput#comment-1
	  $textarea.keyup(function() {
		    $preview.html(convert($textarea.val()));
	  }).trigger('keyup');
	});

</script>