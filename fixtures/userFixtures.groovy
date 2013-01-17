import braid.Role
import braid.User
import braid.UserRole

fixture{
	
		yoda(User,
		name:'Yoda',
		username:'yoda',
		password:'yoda',
		enabled: true)
	
		obi(User,
		name:'Obi Wan Kenobi',
		username:'obi',
		password:'obi',
		enabled: true)
	
		anakin(User,
		name:'Anakin Skywalker',
		username:'anakin',
		password:'anakin',
		enabled: true)
	
		jarjar(User,
		name:'Jar Jar Binks',
		username:'jarjar',
		password:'jarjar',
		enabled: true)
	
		rolYoda(Role, authority: 'YODA')
		rolJedi(Role, authority: 'JEDI')
		rolPadawan(Role, authority: 'PADAWAN')
		rolJarJar(Role, authority: 'JAR_JAR')
	
		ur1(UserRole, user: yoda, role: rolYoda)
		ur2(UserRole, user: obi, role: rolJedi)
		ur3(UserRole, user: anakin, role: rolPadawan)
		ur4(UserRole, user: jarjar, role: rolJarJar)
	}