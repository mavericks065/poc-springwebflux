package au.com.nig.domain.port

import au.com.nig.domain.model.UserDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.UUID

interface IUserInteractor {
    fun findUser(id: Long): Mono<UserDto>

    fun findUsers(preferredName: String? = null, fromCreatedDate: LocalDateTime? = null): Flux<UserDto>

    fun findUserDocument(userId: Long, documentId: UUID): Mono<ByteArray>
}
