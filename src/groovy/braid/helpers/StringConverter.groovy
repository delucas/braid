package braid.helpers

import org.springframework.util.StringUtils

class StringConverter {

	static capitalizeWords(theString) {
		theString.split().collect { StringUtils.capitalize(it.toLowerCase()) }.join(" ")
	}
	
}
