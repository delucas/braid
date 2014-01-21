package braid.helpers
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

@TestMixin(GrailsUnitTestMixin)
class ListSorterSpec extends Specification {

	void "when position less than zero on shiftUp throws Exception"() {
		when:
			def theList = [1, 2, 3, 4]
			ListSorter.shiftUp(theList, -1)
		then:
			thrown IllegalArgumentException
	}
	
	void "when position more than size on shiftUp throws Exception"() {
		when:
			def theList = [1, 2, 3, 4]
			ListSorter.shiftUp(theList, 4)
		then:
			thrown IllegalArgumentException
	}
	
	void "when position less than zero on shiftDown throws Exception"() {
		when:
			def theList = [1, 2, 3, 4]
			ListSorter.shiftDown(theList, -1)
		then:
			thrown IllegalArgumentException
	}
	
	void "when position more than size on shiftDown throws Exception"() {
		when:
			def theList = [1, 2, 3, 4]
			ListSorter.shiftDown(theList, 4)
		then:
			thrown IllegalArgumentException
	}
		
	void "empty list shiftUp returns empty list"() {
		given:
			def theList = []
		expect:
			[] == ListSorter.shiftUp(theList, 0)
	}

	void "single element list shiftUp returns same list"() {
		given:
			def theList = [0]
		expect:
			[0] == ListSorter.shiftUp(theList, 0)
	}
	
	void "two elements list shiftUp on first element returns same list"() {
		given:
			def theList = [0, 1]
		expect:
			[0, 1] == ListSorter.shiftUp(theList, 0)
	}
	
	void "two elements list shiftUp on second element"() {
		given:
			def theList = [0, 1]
		expect:
			[1, 0] == ListSorter.shiftUp(theList, 1)
	}
	
	void "two different elements list shiftUp on second element"() {
		given:
			def theList = [1, 2]
		expect:
			[2, 1] == ListSorter.shiftUp(theList, 1)
	}
	
	void "three different elements list shiftUp on second element"() {
		given:
			def theList = [1, 2, 3]
		expect:
			[2, 1, 3] == ListSorter.shiftUp(theList, 1)
	}
	
	void "three different elements list shiftUp on third element"() {
		given:
			def theList = [1, 2, 3]
		expect:
			[1, 3, 2] == ListSorter.shiftUp(theList, 2)
	}
	
	void "four different elements list shiftUp on third element"() {
		given:
			def theList = [1, 2, 3, 4]
		expect:
			[1, 3, 2, 4] == ListSorter.shiftUp(theList, 2)
	}

	
	void "empty list shiftDown returns empty list"() {
		given:
			def theList = []
		expect:
			[] == ListSorter.shiftDown(theList, 0)
	}

	void "single element list shiftDown returns same list"() {
		given:
			def theList = [0]
		expect:
			[0] == ListSorter.shiftDown(theList, 0)
	}
	
	void "two elements list shiftDown on first element returns same list"() {
		given:
			def theList = [0, 1]
		expect:
			[1, 0] == ListSorter.shiftDown(theList, 0)
	}
	
	void "two elements list shiftDown on second element"() {
		given:
			def theList = [0, 1]
		expect:
			[0, 1] == ListSorter.shiftDown(theList, 1)
	}
	
	void "two different elements list shiftDown on first element"() {
		given:
			def theList = [1, 2]
		expect:
			[2, 1] == ListSorter.shiftDown(theList, 0)
	}
	
	void "three different elements list shiftDown on second element"() {
		given:
			def theList = [1, 2, 3]
		expect:
			[1, 3, 2] == ListSorter.shiftDown(theList, 1)
	}
	
	void "three different elements list shiftDown on third element"() {
		given:
			def theList = [1, 2, 3]
		expect:
			[1, 2, 3] == ListSorter.shiftDown(theList, 2)
	}
	
	void "three different elements list shiftDown on first element"() {
		given:
			def theList = [1, 2, 3]
		expect:
			[2, 1, 3] == ListSorter.shiftDown(theList, 0)
	}
	
	void "four different elements list shiftDown on third element"() {
		given:
			def theList = [1, 2, 3, 4]
		expect:
			[1, 2, 4, 3] == ListSorter.shiftDown(theList, 2)
	}
		
}
