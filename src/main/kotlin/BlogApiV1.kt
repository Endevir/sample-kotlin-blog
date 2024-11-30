package ru.endevir

import features.blog.entity.BlogPostCreateEditDTO
import features.blog.repository.BlogPostRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureBlogAPIV1(repository: BlogPostRepository, apiPrefix: String) {
    routing {
        route("$apiPrefix/posts") {
            get {
                val posts = repository.allBlogPosts()
                call.respond(posts)
            }
            get("/searchByTitle/{title}") {
                val title = call.parameters["title"]
                if (title == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val posts = repository.blogPostsByTitleSearch(title)
                call.respond(posts)
            }
            get("/searchByContent/{content}") {
                val content = call.parameters["content"]
                if (content == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val posts = repository.blogPostsByContentSearch(content)
                call.respond(posts)
            }

            post {
                try {
                    val postData = call.receive<BlogPostCreateEditDTO>()
                    val newPost = repository.addBlogPost(postData)
                    call.respond(HttpStatusCode.Created, newPost)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            put() {
                try {
                    val post = call.receive<BlogPostCreateEditDTO>()
                    val updatedPost = repository.updateBlogPost(post)
                    call.respond(updatedPost)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: JsonConvertException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{postId}") {
                val idParam = call.parameters["postId"]
                if (idParam == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                val id = UUID.fromString(idParam)
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }

                if (repository.removeBlogPost(id)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
