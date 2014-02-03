import grails.util.GrailsUtil

class BootStrap {
	
	def fixtureLoader
	
    def init = { servletContext ->

		if (GrailsUtil.environment == 'development') {

			TimeZone.setDefault(TimeZone.getTimeZone('UTC'))

//			fixtureLoader.include('userFixtures')
//			fixtureLoader.include('courseFixtures')
//			fixtureLoader.load('announcementFixtures')
			fixtureLoader.load('homeworkFixtures')
		}

    }

    def destroy = {
    }
}
