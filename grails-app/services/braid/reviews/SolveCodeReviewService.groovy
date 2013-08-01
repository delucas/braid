package braid.reviews

class SolveCodeReviewService {

	CodeReviewSolution buildSolution(CodeReviewSolutionCommand command, CodeReviewHomework homework, user) {
		CodeReviewSolution solution = command.toEntity()
		solution.user = user
		solution.homework = homework
		solution
	}
	
}
