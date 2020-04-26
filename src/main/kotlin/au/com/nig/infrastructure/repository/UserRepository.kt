package au.com.nig.infrastructure.repository

import au.com.nig.domain.model.User
import au.com.nig.domain.port.IUserRepository
import au.com.nig.domain.unwrap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.domain.Specification.where
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

class UserRepository(val entityRepository: UserEntityRepository) : IUserRepository {
    @Autowired
    private lateinit var jdbcScheduler: Scheduler

    override fun findUser(id: Long): Mono<User> {
        return Mono
            .fromCallable {
                this.entityRepository
                    .findById(id)
                    .unwrap("user not found")
                    .let { it.toUser() }
            }
            .subscribeOn(jdbcScheduler)
    }

    override fun findUsers(): Flux<User> {
        return Flux
            .defer {
                Flux.fromIterable(entityRepository.findAll().map { it.toUser() })
            }
            .subscribeOn(jdbcScheduler)
    }

    override fun findUsersByPreferredNameAndFromCreatedDate(
        preferredName: String?,
        fromCreatedDate: LocalDateTime?
    ): Flux<User> {
        return Flux
            .defer {
                Flux.fromIterable(
                    entityRepository
                        .findAll(buildSpecifications(preferredName, fromCreatedDate))
                        .map { it.toUser() })
            }
            .subscribeOn(jdbcScheduler)
    }

    fun buildSpecifications(
        preferredName: String?,
        fromCreatedDate: LocalDateTime?): Specification<UserEntity> {

        val emptySpecification: Specification<UserEntity> = where(null)
        val preferredNameSpec = if (preferredName != null)
            Specification<UserEntity> { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<String>("preferredName"), preferredName)
            } else null
        val userCreateDateSpec = if (fromCreatedDate != null)
            Specification<UserEntity> { root, _, criteriaBuilder ->
            criteriaBuilder.greaterThanOrEqualTo(
                root.get<LocalDateTime>("userCreatedDate"),
                fromCreatedDate
            )
        } else null
        return listOf(preferredNameSpec, userCreateDateSpec)
            .filterNotNull()
            .fold(emptySpecification) { acc, spec -> acc.and(spec) }
    }
}

@Entity(name = "user_entity")
@Table(name = "user_entity")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Long,
    @Column(name = "email", nullable = true)
    val email: String? = null,
    @Column(name = "firstname", nullable = true)
    val firstName: String? = null,
    @Column(name = "lastname", nullable = true)
    val lastName: String? = null,
    @Column(name = "preferred_name", nullable = true)
    val preferredName: String? = null,
    @Column(name = "phone_number", nullable = true)
    val phoneNumber: String? = null,
    @Column(name = "created_date", nullable = false)
    val userCreatedDate: LocalDateTime? = null
) {
    fun toUser(): User = User(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        preferredName = this.preferredName,
        phoneNumber = this.phoneNumber,
        userCreatedDate = this.userCreatedDate
    )
}

@Repository
interface UserEntityRepository : CrudRepository<UserEntity, Long>,
    JpaSpecificationExecutor<UserEntity> {
    // fun findByPreferredNameAndUserCreatedDate(
    //     preferredName: String?,
    //     userCreatedDate: LocalDateTime?
    // ): List<UserEntity>
}
