modules = {
	common {
		resource url:'css/main.css', disposition: 'head'
	}
    application {
        resource url:'js/application.js'
    }
	textEditor {
		resource url:'css/editor/wysiwyg-color.css', disposition: 'head'
		resource url:'css/editor/bootstrap-wysihtml5.css', disposition: 'head'
		resource url:'js/editor/wysihtml5-0.3.0.js'
		resource url:'js/editor/bootstrap-wysihtml5.js'
	}
}