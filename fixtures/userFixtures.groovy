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
		username:'obi',
		password:'obi',
		enabled: true)
	
		anakin(User,
		name:'Anakin Skywalker',
		dni:'32313123',
		username:'anakin',
		password:'anakin',
		enabled: true)
	
		jarjar(User,
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