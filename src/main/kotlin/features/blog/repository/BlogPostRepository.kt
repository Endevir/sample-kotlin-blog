package features.blog.repository

import features.blog.entity.BlogPost
import features.blog.entity.BlogPostCreateEditDTO
import java.util.UUID

interface BlogPostRepository {
    suspend fun allBlogPosts(): List<BlogPost>
    suspend fun blogPostById(id: UUID): BlogPost?
    suspend fun blogPostsByTitleExact(title: String): BlogPost?
    suspend fun blogPostsByTitleSearch(title: String): List<BlogPost>
    suspend fun blogPostsByContentSearch(content: String): List<BlogPost>
    suspend fun addBlogPost(post: BlogPostCreateEditDTO): BlogPost
    suspend fun updateBlogPost(postChanges: BlogPostCreateEditDTO): BlogPost
    suspend fun removeBlogPost(id: UUID): Boolean
}
