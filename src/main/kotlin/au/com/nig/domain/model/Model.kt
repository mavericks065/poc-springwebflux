package au.com.nig.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: Long,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val preferredName: String? = null,
    val phoneNumber: String? = null,
    val userCreatedDate: LocalDateTime? = null,
    val documentIds: List<UUID> = emptyList()
)

data class Document(
    val userId: Long? = null,
    val companyId: Long? = null,
    val metadata: DocumentMetadata
)

data class DocumentMetadata(
    val uuid: UUID,
    val originalFileName: String,
    val category: DocumentCategory,
    val status: DocumentStatus
)

enum class DocumentCategory(val label: String) {
    PASSPORT("Passport"),
    ID("ID"),
    DRIVER_LICENSE("Driver license"),
    ABN("ABN registration"),
    OTHER("Other");

    companion object {
        fun getDocCategory(str: String): DocumentCategory = when (str) {
            "Passport" -> PASSPORT
            "ID" -> ID
            "Driver license" -> DRIVER_LICENSE
            "ABN registration" -> ABN
            else -> OTHER
        }
    }
}

enum class DocumentStatus {
    UNCHECKED,
    VALIDATED,
    REJECTED;

    companion object {
        fun fromString(string: String): DocumentStatus {
            return when (string) {
                "UNCHECKED" -> UNCHECKED
                "VALIDATED" -> VALIDATED
                else -> REJECTED
            }
        }
    }
}

data class Company(
    val id: Long,
    val email: String? = null,
    val name: String? = null,
    val phoneNumber: String? = null,
    val abn: String? = null,
    val abnType: String? = null,
    val postcode: String? = null,
    val country: String? = null,
    val city: String? = null,
    val address: String? = null
)


