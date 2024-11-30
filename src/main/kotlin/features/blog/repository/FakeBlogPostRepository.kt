package ru.endevir.features.blog.repository

import features.blog.entity.BlogPost
import features.blog.entity.BlogPostCreateEditDTO
import features.blog.repository.BlogPostRepository
import kotlinx.datetime.Clock
import java.util.UUID
import kotlin.time.Duration

class FakeBlogPostRepository: BlogPostRepository {
    private val testPosts: MutableList<BlogPost> = mutableListOf(
        BlogPost(UUID.randomUUID(), "Тестовый пост блога 1", "Lorem ipsum odor amet, consectetuer adipiscing elit. Habitasse sem duis hac quam hac, natoque dui. Lacinia eu est orci velit platea eleifend. Ultrices imperdiet duis; felis aptent praesent sit. Convallis id platea malesuada est odio viverra posuere euismod risus. Neque nostra aliquam interdum, non vestibulum eros aenean. Nam congue magnis netus metus quam. Magnis ac ornare purus posuere habitant odio.", Clock.System.now().minus(Duration.parse("1h")), Clock.System.now().minus(Duration.parse("10m"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 2", "Nam mauris augue libero porta eleifend duis vel platea. Dolor volutpat vestibulum netus laoreet accumsan. Ex malesuada sollicitudin elit tincidunt; mattis odio. Posuere sollicitudin quam risus est magna fringilla risus. Nullam enim ligula taciti facilisi ridiculus magna phasellus arcu. Tellus fermentum tristique purus phasellus porta at sodales mollis litora. Ut aliquet pellentesque ut viverra litora per sodales.", Clock.System.now().minus(Duration.parse("2d 5h")), Clock.System.now().minus(Duration.parse("2d 5h"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 3", "Duis cubilia hendrerit leo lacinia proin. Eu conubia egestas etiam accumsan nisi. Gravida commodo pharetra vitae ipsum eget libero sit luctus. Mi convallis conubia pretium imperdiet nisl pharetra efficitur. Turpis eget platea malesuada pretium primis luctus. Non enim nibh posuere etiam tortor quisque rutrum blandit. Sit faucibus tincidunt senectus at cubilia diam venenatis. Natoque tortor eros maecenas a enim nullam.", Clock.System.now().minus(Duration.parse("4d")), Clock.System.now().minus(Duration.parse("2d"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 4", "Magnis dis finibus parturient elementum et fusce. Dignissim vulputate sit fames metus accumsan est a. Aenean efficitur ex blandit vitae suscipit felis vulputate amet pretium. Facilisis fusce non diam curabitur viverra dapibus. Duis ligula amet vivamus habitant pulvinar. Mauris feugiat elementum metus dignissim quam. Curae purus integer est efficitur primis.", Clock.System.now().minus(Duration.parse("7d")), Clock.System.now().minus(Duration.parse("4d 30m"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 5", "Cras ridiculus ridiculus vel sagittis aliquam ut aliquet platea viverra. Posuere eleifend dictum class fames ante accumsan aliquet. Ex eros ultricies cras etiam aliquet. Pharetra ipsum malesuada nullam arcu laoreet. Fusce integer consequat tristique adipiscing lacinia placerat erat lorem. Consequat tempor tristique leo id lacus; mi aliquam odio. Integer mi nostra egestas vivamus pulvinar vulputate convallis. Feugiat nisi praesent porttitor faucibus, tortor ante lectus mus feugiat. Magnis a ante accumsan elit; varius varius ullamcorper.", Clock.System.now().minus(Duration.parse("15d")), Clock.System.now().minus(Duration.parse("15d"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 6", "Semper feugiat varius leo aliquet egestas platea. Habitasse velit torquent, mus fames ornare natoque. Molestie lorem orci aliquam pulvinar nunc; lacus odio. Consectetur fermentum venenatis dignissim finibus porta. Feugiat tincidunt convallis facilisi dui maecenas vestibulum. Arcu orci sollicitudin hendrerit; praesent neque ac venenatis natoque nibh.", Clock.System.now().minus(Duration.parse("30d")), Clock.System.now().minus(Duration.parse("30d"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 7", "Ultricies feugiat ultrices proin a vulputate lacus leo. Eros platea tristique fermentum nec, dis aenean lectus. Curabitur tortor id taciti curae adipiscing eu amet praesent rutrum. Phasellus ridiculus fermentum mattis gravida montes faucibus augue vulputate. Fusce suspendisse consequat fringilla maximus eu cursus phasellus lobortis! Senectus nulla suscipit penatibus elit nullam. Tempus ex fermentum; platea nisi velit nullam.", Clock.System.now().minus(Duration.parse("20d")), Clock.System.now().minus(Duration.parse("20d"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 8", "Aliquet quisque venenatis nunc facilisi ut fusce per. Ultricies proin vitae ullamcorper suspendisse ridiculus aliquam ornare. Mauris aliquam commodo mollis praesent integer malesuada ridiculus eros. Posuere urna accumsan sapien vitae cursus pulvinar tellus. Mollis maximus aenean montes feugiat torquent. Mus finibus eget pulvinar, purus ante venenatis neque cursus mauris. Venenatis semper lacinia mi bibendum pulvinar ante finibus vel nascetur.", Clock.System.now().minus(Duration.parse("50d")), Clock.System.now().minus(Duration.parse("10m"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 9", "Mus eget turpis parturient massa dictumst phasellus adipiscing mauris ante. Inceptos sed pellentesque massa nibh velit dui odio conubia aliquam. Libero laoreet nullam amet ante euismod eu taciti. Quisque lacinia cubilia nam aliquam scelerisque. Morbi primis lectus litora leo maecenas maecenas dis natoque ornare. Odio scelerisque magna malesuada vehicula dignissim diam. Posuere quam platea primis dictum pretium? Accumsan mi pharetra primis, fringilla aptent aptent. Diam volutpat rutrum faucibus; pretium sagittis tempor sit auctor volutpat.", Clock.System.now().minus(Duration.parse("50d 10m")), Clock.System.now().minus(Duration.parse("50d 10m"))),
        BlogPost(UUID.randomUUID(),"Тестовый пост блога 10", "Egestas finibus sed dapibus commodo ac tempus dis. Porttitor suspendisse habitant consectetur phasellus gravida natoque pulvinar. Erat nisi et ante natoque dapibus curabitur; congue hendrerit nec. Per varius platea torquent netus quisque cursus interdum tempor. Luctus ultricies odio nibh; sed facilisi dapibus maecenas convallis. Ornare torquent habitasse hendrerit class auctor. Adipiscing sagittis ut hendrerit hac vehicula tincidunt. Taciti blandit eu mauris condimentum suscipit quisque.", Clock.System.now().minus(Duration.parse("100d")), Clock.System.now().minus(Duration.parse("2m"))),
    )

    override suspend fun blogPostById(id: UUID): BlogPost? = testPosts.find { it.id == id }

    override suspend fun blogPostsByTitleExact(title: String): BlogPost? = testPosts.find { it.title == title }

    override suspend fun blogPostsByTitleSearch(title: String): List<BlogPost> = testPosts.filter { it.title.lowercase().contains(title.lowercase()) }

    override suspend fun blogPostsByContentSearch(content: String): List<BlogPost> = testPosts.filter { it.content.lowercase().contains(content.lowercase()) }

    override suspend fun allBlogPosts(): List<BlogPost> = testPosts

    override suspend fun addBlogPost(post: BlogPostCreateEditDTO): BlogPost {
        if (blogPostsByTitleExact(post.title) != null) {
            throw IllegalStateException("Blog post with that title already exists")
        }
        val blogPost = BlogPost(UUID.randomUUID(), post.title, post.content, Clock.System.now(), Clock.System.now())
        testPosts.add(blogPost)
        return blogPost
    }

    override suspend fun updateBlogPost(postChanges: BlogPostCreateEditDTO): BlogPost {
        if (postChanges.id == null) {
            throw IllegalStateException("Blog post id should be specified")
        }

        var post = blogPostById(postChanges.id!!) ?: throw IllegalStateException("Blog post with such id not exists")
        post.title = postChanges.title
        post.content = postChanges.content
        post.changedAt = Clock.System.now()
        return post
    }

    override suspend fun removeBlogPost(id: UUID): Boolean {
        return testPosts.removeIf { it.id == id}
    }
}
