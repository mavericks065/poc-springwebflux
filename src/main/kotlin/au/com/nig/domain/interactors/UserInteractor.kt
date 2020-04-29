package au.com.nig.domain.interactors

import au.com.nig.domain.model.UserDto
import au.com.nig.domain.port.IUserInteractor
import au.com.nig.domain.port.IUserRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

class UserInteractor(val userRepository: IUserRepository) : IUserInteractor {

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

    override fun findUserDocument(userId: Long, documentId: Long): Mono<ByteArray> {
        TODO("not implemented")
    }
}
