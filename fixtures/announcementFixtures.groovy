import braid.Announcement

load "courseFixtures"

fixture{

	a1(Announcement,
		text: 'portarse bien <strong>deben</strong>',
		dateCreated: new Date() - 2,
		course: taller,
		announcer: yoda
	)

	a2(Announcement,
		text: 'bienvenidos al <strong>Taller Web</strong>',
		dateCreated: new Date() - 1,
		course: taller,
		announcer: obi
	)
}