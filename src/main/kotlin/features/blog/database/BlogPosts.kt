package features.blog.database
import features.blog.entity.BlogPost
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

object BlogPostsTable : UUIDTable("blog_posts"){
    val title = varchar("title", 100)
    val content = text("content")
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val changedAt = datetime("changed_at").defaultExpression(CurrentDateTime)
}

class BlogPostDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<BlogPostDAO>(BlogPostsTable)
    var title by BlogPostsTable.title
    var content by BlogPostsTable.content
    var createdAt by BlogPostsTable.createdAt
    var changedAt by BlogPostsTable.changedAt
}

fun daoToModel(dao: BlogPostDAO) = BlogPost(
    id=dao.id.value,
    title=dao.title,
    content = dao.content,
    createdAt = dao.createdAt.toInstant(TimeZone.UTC),
    changedAt = dao.changedAt.toInstant(TimeZone.UTC)
)

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)
