modules = {
	common {
		resource url:'css/main.css', disposition: 'head'
	}
    application {
        resource url:'js/application.js'
    }
	markdown {
		resource url:'js/Markdown.Converter.js'
		resource url:'js/Markdown.Sanitizer.js'
	}
}