package au.com.nig.application

import au.com.nig.domain.interactors.UserInteractor
import au.com.nig.domain.port.IUserInteractor
import au.com.nig.domain.port.IUserRepository
import au.com.nig.infrastructure.repository.UserEntityRepository
import au.com.nig.infrastructure.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers

@Configuration
class InjectionConfiguration {
    @Bean
    fun getUserRepostory(entityRepository: UserEntityRepository): IUserRepository = UserRepository(entityRepository)

    @Bean
    fun getUserInteractor(userRepository: IUserRepository): IUserInteractor = UserInteractor(userRepository)

    @Bean
    fun getAReactorScheduler(): Scheduler = Schedulers.boundedElastic()

}
