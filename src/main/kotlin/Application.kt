package ru.endevir

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.swagger.*
import kotlinx.serialization.json.Json
import ru.endevir.features.blog.repository.FakeBlogPostRepository

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


fun Application.module() {
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }
    install(CallLogging)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        swaggerUI(path = "/api", swaggerFile = "openapi/documentation.yaml")
    }
    configureBlogAPIV1(FakeBlogPostRepository(), "/api/v1")
    configureDatabases()
}
