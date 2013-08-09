package braid.reviews

import braid.User

class ReviewSolutionService {

	CodeReviewSolution fetch(CodeReviewHomework homework, currentUser) {
		// TODO: fetch solutions with less revisions not by currentUser (HQL)

		def reviewsMade = CodeReview.createCriteria().count() {
			createAlias('solution', 'solution')
			eq('solution.homework', homework)
			eq('user', currentUser)
		}
		
		if (reviewsMade >= homework.requiredReviews) {
			return ownSolution(homework, currentUser)
		}
		
		def solutions = CodeReviewSolution.where { user != currentUser }.list()
		solutions.retainAll { CodeReview.findAllBySolutionAndUser(it, currentUser).empty }
		solutions.sort { a, b -> a.revisionsTotal <=> b.revisionsTotal } 
		solutions.size() > 0 ? solutions[0] : null
	}
	
	CodeReview buildReview(CodeReviewCommand command, CodeReviewSolution solution, currentUser) {
		CodeReview review = command.toEntity()
		review.user = currentUser
		review.solution = solution
		review.save()

//		solution.addToReviews(review)
//		solution.save()
	}

	private ownSolution(CodeReviewHomework homework, User currentUser) {
		def ownSolutionReviews = CodeReview.createCriteria().count() {
			createAlias('solution', 'solution')
			eq('solution.homework', homework)
			eq('solution.user', currentUser)
			eq('user', currentUser)
		}
		
		if (ownSolutionReviews == 0) CodeReviewSolution.findByHomeworkAndUser(homework, currentUser)
		else null
	}
}
