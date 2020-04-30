package au.com.nig.domain.port

import reactor.core.publisher.Mono
import java.util.UUID

interface IDocumentRepository {
    fun retrieveDocument(userId: Long, documentId: UUID): Mono<ByteArray>
}
