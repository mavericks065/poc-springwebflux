package au.com.nig.domain.interactors

import au.com.nig.domain.model.UserDto
import au.com.nig.domain.port.IDocumentRepository
import au.com.nig.domain.port.IUserInteractor
import au.com.nig.domain.port.IUserRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.util.UUID

class UserInteractor(
    val userRepository: IUserRepository,
    val documentRepository: IDocumentRepository
) : IUserInteractor {

    override fun findUsers(preferredName: String?, fromCreatedDate: LocalDateTime?): Flux<UserDto> {
        return if (preferredName == null && fromCreatedDate == null)
            userRepository
                .findUsers()
                .map { UserDto(it) }
        else
            userRepository
                .findUsersByPreferredNameAndFromCreatedDate(preferredName, fromCreatedDate)
                .map { UserDto(it) }
    }

    override fun findUser(id: Long): Mono<UserDto> {
        return userRepository.findUser(id)
            .map { UserDto(it) }
    }

    override fun findUserDocument(userId: Long, documentId: UUID): Mono<ByteArray> {
        return userRepository
            .findUser(userId)
            .flatMap { user ->
                user
                    .documentIds
                    .find { it == documentId }
                    ?.let { documentRepository.retrieveDocument(userId, it) }
            }
    }
}
