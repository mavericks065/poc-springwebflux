package au.com.nig.domain.port

import au.com.nig.domain.model.UserDto
import net.bytebuddy.asm.Advice
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

interface IUserInteractor {
    fun findUser(id: Long): Mono<UserDto>

    fun findUsers(preferredName: String? = null, fromCreatedDate: LocalDateTime? = null): Flux<UserDto>
}