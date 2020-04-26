package au.com.nig.domain.model

data class UserDto(val id: Long,
                   val email: String? = null,
                   val firstName: String? = null,
                   val lastName: String? = null,
                   val preferredName: String? = null,
                   val phoneNumber: String? = null) {
    constructor(user: User) : this(
            id = user.id,
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            preferredName = user.preferredName,
            phoneNumber = user.phoneNumber
    )


}
