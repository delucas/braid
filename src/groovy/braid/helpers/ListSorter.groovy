package braid.helpers

import org.springframework.util.StringUtils

class ListSorter {

	static shiftUp(list, position = 0) {
		
		if ((list && position >= list.size()) || position < 0) {
			throw new IllegalArgumentException()
		}
		
		def result = []
		
		if (list && position) {
			
			if (position >= 2) {
				result += list[0 ..< position-1]
			}
			
			result += list[position, position - 1]
			
			if (list.size() - 1 > position) {
				result += list[(position + 1) .. (list.size() - 1)]
			}

		} else {
			result = list
		}
		
		result
	}
	
	static shiftDown(list, position = 0) {
		if ((list && position >= list.size()) || position < 0) {
			throw new IllegalArgumentException()
		}

		if (position == list.size() - 1)
			list
		else
			shiftUp(list, position + 1)
	}
	
}
