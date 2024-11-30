package features.blog.repository

import features.blog.entity.BlogPost
import features.blog.entity.BlogPostCreateEditDTO
import java.util.UUID

interface BlogPostRepository {
    fun allBlogPosts(): List<BlogPost>
    fun blogPostById(id: UUID): BlogPost?
    fun blogPostsByTitleExact(title: String): BlogPost?
    fun blogPostsByTitleSearch(title: String): List<BlogPost>
    fun blogPostsByContentSearch(content: String): List<BlogPost>
    fun addBlogPost(post: BlogPostCreateEditDTO): BlogPost
    fun updateBlogPost(postChanges: BlogPostCreateEditDTO): BlogPost
    fun removeBlogPost(id: UUID): Boolean
}
