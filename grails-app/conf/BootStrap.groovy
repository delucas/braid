import grails.util.GrailsUtil

class BootStrap {
	
	def fixtureLoader
	
    def init = { servletContext ->
		
		
		if (GrailsUtil.environment == 'development') {
			
//				def db = mongo.getDB('braid')
//				
//				db.assignment.drop()
//				db.assignmentSolution.drop()
//				db.homework.drop()
//				db.role.drop()
//				db.user.drop()
//				db.userRole.drop()
//	
//				db['assignment.next_id'].drop()
//				db['assignmentSolution.next_id'].drop()
//				db['homework.next_id'].drop()
//				db['role.next_id'].drop()
//				db['user.next_id'].drop()
//				db['userRole.next_id'].drop()
				
//			fixtureLoader.include('userFixtures')
//			fixtureLoader.include('courseFixtures')
//			fixtureLoader.load('questionFixtures')
//			fixtureLoader.load('announcementFixtures')
			//fixtureLoader.load('homeworkFixtures')
			fixtureLoader.load('homeworkFixtures')
		}
    }
    def destroy = {
    }
}
