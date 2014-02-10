package braid.course

import braid.Role
import braid.User
import braid.UserRole

class PromoteUserService {

	public promote(User user, course) {

		removeAllRoles(user)
		assignRole(user, Role.findByAuthority('ROLE_JEDI')) 
	}

	public demote(User user, course) {

		removeAllRoles(user)

		assignRole(user, Role.findByAuthority('ROLE_PADAWAN'))
	}

	private removeAllRoles(user) {
		UserRole.removeAll(user)
	}

	private assignRole(user, role) {
		UserRole.create(user, role, true)
	}
}
