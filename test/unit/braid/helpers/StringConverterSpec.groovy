package braid.helpers
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class StringConverterSpec extends Specification {
	
	void "does not convert already capitalized string"() {
		given: 'a capitalized string'
			def string = 'Capitalized String'
		expect: 'does not convert string'
			'Capitalized String' == StringConverter.capitalizeWords(string)
	}
	
	void "converts lowecase string to capitalized string"() {
		given: 'a lowercase string'
			def string = 'lowercase string'
		expect: 'converter changes casing'
			'Lowercase String' == StringConverter.capitalizeWords(string)
	}

	void "converts uppercase string to capitalized string"() {
		given: 'an uppercase string'
			def string = 'UPPERCASE STRING'
		expect: 'converter changes casing'
			'Uppercase String' == StringConverter.capitalizeWords(string)
	}
	
}
