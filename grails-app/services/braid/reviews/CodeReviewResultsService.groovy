package braid.reviews

class CodeReviewResultsService {

	CodeReviewSolution getOwnSolution(CodeReviewHomework homework, user) {
		CodeReviewSolution.findByHomeworkAndUser(homework, user)
	}

	def findAllReviews(solution) {
		solution ? CodeReview.findAllBySolution(solution) : []
	}
		
}
