import java.util.Date;

import braid.Announcement
import braid.User;

load "courseFixtures"

fixture{

	a1(Announcement,
			text: 'portarse bien **deben**',
			dateCreated: new Date() - 2,
			course: taller,
			announcer: yoda
			)

	a2(Announcement,
			text: 'bienvenidos al **Taller Web**',
			dateCreated: new Date() - 1,
			course: taller,
			announcer: obi
			)
}