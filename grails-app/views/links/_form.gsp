<div
	class="control-group ${hasErrors(bean:link, field:'caption', 'error')}">
	<label class="control-label" for="inputCaption">Etiqueta</label>
	<div class="controls">
		<input type="text" id="inputCaption" name="caption"
			value="${link?.caption}"
			class="span8 ${hasErrors(bean:link, field:'caption', 'error')}"
			placeholder="Escriba una etiqueta para el enlace">
	</div>
</div>

<div
	class="control-group ${hasErrors(bean:link, field:'url', 'error')}">
	<label class="control-label" for="inputURL">URL</label>
	<div class="controls">
		<input type="text" id="inputURL" name="url" value="${link?.url}"
			class="span8 ${hasErrors(bean:link, field:'url', 'error')}"
			placeholder="Escriba una URL para el enlace">
	</div>
</div>