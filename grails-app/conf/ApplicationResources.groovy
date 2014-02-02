modules = {
	common {
		dependsOn 'font-awesome'
		resource url: 'css/main.css', disposition: 'head'
	}
    application {
        resource url: 'js/application.js'
    }
	textEditor {
		resource url: 'css/editor/wysiwyg-color.css', disposition: 'head'
		resource url: 'css/editor/bootstrap-wysihtml5.css', disposition: 'head'
		resource url: 'js/editor/wysihtml5-0.3.0.js'
		resource url: 'js/editor/bootstrap-wysihtml5.js'
	}
	colorPicker {
		resource url: 'css/colorpicker/jquery.minicolors.css', disposition: 'head'
		resource url: 'js/colorpicker/jquery.minicolors.min.js'
	}
}