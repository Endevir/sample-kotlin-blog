package features.blog.entity

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import ru.endevir.utils.serializers.UUIDSerializer
import java.util.UUID

@Serializable
data class BlogPost(
    @Serializable(with = UUIDSerializer::class)
    var id: UUID?,
    var title: String,
    var content: String,
    var createdAt: Instant,
    var changedAt: Instant
)

@Serializable
data class BlogPostCreateEditDTO(
    @Serializable(with = UUIDSerializer::class)
    var id: UUID? = null,
    var title: String,
    var content: String
)