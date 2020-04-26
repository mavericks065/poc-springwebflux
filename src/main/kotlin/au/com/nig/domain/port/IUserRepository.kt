package au.com.nig.domain.port

import au.com.nig.domain.model.User
import au.com.nig.domain.model.UserDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface IUserRepository {
    fun findUser(id: Long): Mono<User>
    fun findUsers(): Flux<User>
    fun findUsersByPreferredNameAndFromCreatedDate(
        preferredName: String?,
        fromCreatedDate: LocalDateTime?
    ): Flux<User>
}
