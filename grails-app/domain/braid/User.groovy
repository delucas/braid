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

	String email
	String oauthId
	String oauthProvider
	String avatarUrl

	static constraints = {
		username blank: false, unique: true
		password blank: false
		name nullable: true
		dni nullable: true
		email nullable: true
		oauthProvider nullable: true
		avatarUrl nullable: true
		oauthId nullable: true
	}

	static mapping = {
		password column: '`password`'
		table 'braid_user'
	}

	boolean hasRole(String authority) {
		authorities.contains(Role.findByAuthority(authority))
	}

	def getCourses() {
		UserCourse.executeQuery(
				'select uc.course from UserCourse as uc where uc.user = :user and uc.course.active = true',
				[user: this])
	}

	boolean isStudentOf(User jedi) {
		def jedis = []

		courses.each { course ->
			jedis << course.jedis
		}
		jedis.flatten().contains(jedi)
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role as Set
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
