package au.com.nig.application

import au.com.nig.domain.interactors.UserInteractor
import au.com.nig.domain.port.IDocumentRepository
import au.com.nig.domain.port.IUserInteractor
import au.com.nig.domain.port.IUserRepository
import au.com.nig.infrastructure.repository.DocumentRepository
import au.com.nig.infrastructure.repository.UserEntityRepository
import au.com.nig.infrastructure.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers

@Configuration
class InjectionConfiguration {
    @Bean
    fun getUserRepository(entityRepository: UserEntityRepository): IUserRepository = UserRepository(entityRepository)

    @Bean
    fun getDocumentRepository(scheduler: Scheduler): IDocumentRepository = DocumentRepository(scheduler)

    @Bean
    fun getUserInteractor(userRepository: IUserRepository, documentRepository: IDocumentRepository): IUserInteractor =
        UserInteractor(userRepository, documentRepository)

    @Bean
    fun getReactorScheduler(): Scheduler = Schedulers.boundedElastic()

}
