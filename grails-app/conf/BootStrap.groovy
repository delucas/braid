import grails.util.GrailsUtil

class BootStrap {
	
	def fixtureLoader
	
    def init = { servletContext ->

		if (GrailsUtil.environment == 'development') {

			TimeZone.setDefault(TimeZone.getTimeZone('UTC'))

//			fixtureLoader.load('userFixtures')
//			fixtureLoader.load('courseFixtures')
//			fixtureLoader.load('announcementFixtures')
			fixtureLoader.load('homeworkFixtures')
		}

    }

    def destroy = {
    }
}
