package au.com.nig.application.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class UserRouter(private val handler: UserHandler) {
    @Bean
    fun route(): RouterFunction<ServerResponse> = router {
        (accept(APPLICATION_JSON)
                and "/api/v1").nest {
            GET("/users", handler::findUsersByParams)
            GET("/users/{id}", handler::findUser)
            GET("/users/{id}/documents/{documentId}", handler::findUserDocument)
        }
    }
}
