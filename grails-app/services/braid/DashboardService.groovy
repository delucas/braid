package braid

import braid.assignment.AssignmentSolution;
import braid.homework.Homework;

class DashboardService {

	def courseService

	def homeworkGraph() {
		def course = courseService.currentCourse

		def homeworkGraph = Homework.executeQuery(
				"""select hs.homework.id,
			sum(case when hs.feedback.score = 1 then 1 else 0 end) as one,
			sum(case when hs.feedback.score = 2 then 1 else 0 end) as two,
			sum(case when hs.feedback.score = 3 then 1 else 0 end) as three
			from HomeworkSolution hs
			where hs.homework.course.id = ${course.id}
			group by hs.homework.id
			order by hs.homework.id"""
				)

		def homeworkTransformation = { list ->
			def result = [[], [], [], []]
			list.eachWithIndex { it, index ->
				result[0] << index + 1
				result[1] << it[1]
				result[2] << it[2]
				result[3] << it[3]
			}
			result
		}

		try {
			generateBarChartData(homeworkGraph, homeworkTransformation)
		} catch (Exception e) {
			[bars: [], submissions: 0, total: 0, avg: 0]
		}
	}

	def assignmentGraph() {
		def course = courseService.currentCourse

		def assignmentGraph = AssignmentSolution.executeQuery('select sol.assignment.title, count(sol.id) as resueltas ' +
				"from AssignmentSolution sol where sol.score is not null and sol.assignment.course.id = ${course.id} group by sol.assignment.title, sol.assignment.dueDate order by sol.assignment.dueDate")

		def assignmentTransformation = { list ->
			def result = [[], []]
			list.each {
				result[0] << it[0]
				result[1] << it[1]
			}
			result
		}
		try {
			generateBarChartData(assignmentGraph, assignmentTransformation)
		} catch (Exception e) {
			[bars: [], submissions: 0, total: 0, avg: 0]
		}
	}

	private def generateBarChartData(def graphData, transformation) {
		def bars = transformation(graphData)
		def submissions = bars[1].sum()
		def total = bars[0].size()
		[bars: bars, submissions: submissions, total: total, avg: (submissions/total as Double).round(2)]
	}
}
