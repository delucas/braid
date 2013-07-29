package braid.reviews

class CodeReviewController {

	def courseService

	static allowedMethods = [
		list: 'GET',
		solve: ['GET', 'POST'],
		pair: ['GET', 'POST'],
		self: ['GET', 'POST'],
		results: 'GET'
	]

	def list() {
		[homeworkList:
			CodeReviewHomework.findAllByCourse(courseService.currentCourse,
				[sort: 'solutionDueDate', order: 'desc'])]
	}

	def solve() {
		if (request.method == 'GET') {
			println params.id
		} else {
			flash.message = 'TBD'
			redirect action:'list'
		}
	}

	def review() {
		if (request.method == 'GET') {
			//TODO: reuse pair view, with own solution
			println params.id
		} else {
			flash.message = 'TBD'
			redirect action:'list'
		}
	}

	def results() {
	}
}