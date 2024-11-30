package ru.endevir.features.blog.repository

import features.blog.database.BlogPostDAO
import features.blog.database.BlogPostsTable
import features.blog.database.daoToModel
import features.blog.database.suspendTransaction
import features.blog.entity.BlogPost
import features.blog.entity.BlogPostCreateEditDTO
import features.blog.repository.BlogPostRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID


class PgBlogPostRepository: BlogPostRepository {
    override suspend fun allBlogPosts(): List<BlogPost> = suspendTransaction{
        BlogPostDAO.all().map(::daoToModel)
    }

    override suspend fun blogPostById(id: UUID): BlogPost? = suspendTransaction{
        val post = BlogPostDAO.findById(id) ?: return@suspendTransaction null
        return@suspendTransaction daoToModel(post)
    }

    override suspend fun blogPostsByTitleExact(title: String): BlogPost? = suspendTransaction{
        BlogPostDAO
            .find { (BlogPostsTable.title eq title) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun blogPostsByTitleSearch(title: String): List<BlogPost> = suspendTransaction{
        BlogPostDAO
            .find { (BlogPostsTable.title like "%$title%") }
            .map(::daoToModel)
    }

    override suspend fun blogPostsByContentSearch(content: String): List<BlogPost> = suspendTransaction {
        BlogPostDAO
            .find { (BlogPostsTable.content like "%$content%") }
            .map(::daoToModel)
    }

    override suspend fun addBlogPost(post: BlogPostCreateEditDTO): BlogPost = suspendTransaction {
        daoToModel(BlogPostDAO.new {
            title = post.title
            content = post.content
            createdAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            changedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        })
    }

    override suspend fun updateBlogPost(postChanges: BlogPostCreateEditDTO): BlogPost = suspendTransaction {
        if (postChanges.id == null) {
            throw IllegalStateException("Blog post id should be specified")
        }

        val newPost = transaction {
            val post = BlogPostDAO.findById(postChanges.id!!) ?: throw IllegalStateException("Blog post with such id not exists")
            post.title = postChanges.title
            post.content = postChanges.content
            post.changedAt = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            return@transaction post
        }

        return@suspendTransaction daoToModel(newPost)
    }

    override suspend fun removeBlogPost(id: UUID): Boolean = suspendTransaction{
        BlogPostsTable.deleteWhere { BlogPostsTable.id.eq(id) } > 0
    }
}
