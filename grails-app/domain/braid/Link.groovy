package braid

class Link {

	String caption
	String url
	Integer position
	
	static belongsTo = [course: Course]

}
