package braid

class User {

	transient springSecurityService

	String name
	String dni
	
	
	String username
	String password
	
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password blank: false
		name nullable: true
		dni nullable: true
	}

	static mapping = {
		password column: '`password`'
	}
	
	boolean hasRole(String authority) {
		authorities.contains(Role.findByAuthority(authority))
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
