package au.com.nig.application.status

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

data class YoPeople(val apiVersion: String = "") {
    override fun toString(): String {
        return "hello from API $apiVersion"
    }
}

@Configuration
class HealthCheckRouter {

    @Bean
    fun healthCheckRoute() = router {
        GET("/health-check") { _ -> ServerResponse.ok().body(BodyInserters.fromValue(YoPeople().toString())) }
    }

    @Bean
    fun apiRoute() = router {
        (accept(TEXT_HTML) and "/api").nest {
            GET("/internal/v1/status", ::getYoPeopleV1)
            GET("/open/v2/status", ::getYoPeopleV2)
        }
    }

    fun getYoPeopleV1(request: ServerRequest): Mono<out ServerResponse> {
        return ServerResponse.ok().body(BodyInserters.fromValue(YoPeople(" internal v1").toString()))
    }

    fun getYoPeopleV2(request: ServerRequest): Mono<out ServerResponse> {
        return ServerResponse.ok().body(BodyInserters.fromValue(YoPeople("open api v2").toString()))
    }
}
