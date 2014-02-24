import braid.Role
import braid.User
import braid.UserRole

fixture{

	yoda(User,
	name:'Yoda',
	dni:'1',
	username:'yoda',
	password:'yoda',
	enabled: true)

	obi(User,
	name:'Obi Wan Kenobi',
	dni:'12345678',
	username:'obiwan',
	password:'obiwan',
	enabled: true)

	quigon(User,
	name:'Qui Gon Jinn',
	dni:'12876543',
	username:'quigon',
	password:'quigon',
	enabled: true)

	anakin(User,
	name:'Anakin Skywalker',
	dni:'23876543',
	username:'delucas',
	password:'anakin',
	email:'videla.lucas@gmail.com',
	enabled: true)

	leia(User,
	name:'Leia Skywalker',
	dni:'32345677',
	username:'leia',
	password:'leia',
	enabled: true)

	luke(User,
	name:'Luke Skywalker',
	dni:'32345678',
	username:'luke',
	password:'luke',
	enabled: true)

	phantom(User,
	name:'Phantom Menace',
	dni:'1000000',
	username:'phantom',
	password:'phantom',
	enabled: true)

	jarjar(User,
	username:'jarjar',
	password:'jarjar',
	enabled: true)


	rolYoda(Role, authority: 'ROLE_YODA')
	rolJedi(Role, authority: 'ROLE_JEDI')
	rolPadawan(Role, authority: 'ROLE_PADAWAN')
	rolJarJar(Role, authority: 'ROLE_JAR_JAR')


	ur1(UserRole, user: yoda, role: rolYoda)
	ur2a(UserRole, user: obi, role: rolJedi)
	ur2b(UserRole, user: quigon, role: rolJedi)
	ur3a(UserRole, user: anakin, role: rolPadawan)
	ur3b(UserRole, user: luke, role: rolPadawan)
	ur3c(UserRole, user: phantom, role: rolPadawan)
	ur3d(UserRole, user: leia, role: rolPadawan)
	ur4(UserRole, user: jarjar, role: rolJarJar)

}