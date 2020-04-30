package au.com.nig.infrastructure.repository

import au.com.nig.domain.port.IDocumentRepository
import reactor.core.publisher.Mono
import reactor.core.scheduler.Scheduler
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToOne
import javax.persistence.Table

class DocumentRepository(val scheduler: Scheduler) : IDocumentRepository {
    override fun retrieveDocument(userId: Long, documentId: UUID): Mono<ByteArray> {
        return Mono.fromCallable {
            val filePath = "/Users/nicolasguignard-octo/Nicolas/priv_workspace/poc-springwebflux/documents/users/$userId/$documentId.png"
            Files.readAllBytes(Paths.get(filePath))
        } .subscribeOn(scheduler)
    }
}

@Entity(name = "document")
@Table(name = "document")
data class DocumentEntity(
    @Id
    @Column(name = "id", unique = true, nullable = false)
    val id: UUID,
    @ManyToOne
    @JoinTable(
        name = "document_user",
        joinColumns = [JoinColumn(
            name = "document_id",
            referencedColumnName = "id"
        )],
        inverseJoinColumns = [JoinColumn(
            name = "user_entity_id",
            referencedColumnName = "id"
        )]
    )
    val userEntity: UserEntity? = null
)
