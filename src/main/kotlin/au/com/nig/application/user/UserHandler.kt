package au.com.nig.application.user

import au.com.nig.domain.model.UserDto
import au.com.nig.domain.port.IUserInteractor
import au.com.nig.domain.unwrap
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class UserHandler(val interactor: IUserInteractor) {
    companion object {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    }

    fun findUsersByParams(request: ServerRequest): Mono<out ServerResponse> {
        val preferredName = request.queryParam("preferredName").unwrap()
        val fromCreatedDate = request.queryParam("fromCreatedDate")
            .unwrap()
            ?.let {
                LocalDate.parse(it, formatter).atStartOfDay()
            }
        val result = interactor.findUsers(preferredName, fromCreatedDate)
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(result, UserDto::class.java)
    }


    fun findUser(request: ServerRequest): Mono<out ServerResponse> {
        val idToLookFor = request.pathVariable("id").toLong()
        val result = interactor.findUser(idToLookFor)
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(
                result,
                UserDto::class.java
            )
    }
}