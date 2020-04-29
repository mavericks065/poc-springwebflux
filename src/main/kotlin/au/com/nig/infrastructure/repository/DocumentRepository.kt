package au.com.nig.infrastructure.repository

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToOne
import javax.persistence.Table

class DocumentRepository {
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
                        name = "user_id",
                        referencedColumnName = "id"
                )]
        )
        val userEntity: UserEntity? = null
)
