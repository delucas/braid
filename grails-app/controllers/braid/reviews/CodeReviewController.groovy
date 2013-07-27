package braid.reviews

class CodeReviewController {

	static allowedMethods = [
		list: 'GET',
		solve: ['GET', 'POST'],
		pair: ['GET', 'POST'],
		self: ['GET', 'POST'],
		results: 'GET'
	]

	def list() {
	}

	def solve() {
		if (request.method == 'GET') {
			println params.id
		} else {
			flash.message = 'TBD'
			redirect action:'list'
		}
	}

	def pair() {
		if (request.method == 'GET') {
			println params.id
		} else {
			flash.message = 'TBD'
			redirect action:'list'
		}
	}

	def self() {
		if (request.method == 'GET') {
			//TODO: reuse pair view, with own solution
			render view:'pair'
		} else {
			flash.message = 'TBD'
			redirect action:'list'
		}
	}

	def results() {
	}
}