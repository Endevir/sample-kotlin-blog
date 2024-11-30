package ru.endevir

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    val hostname = environment.config.propertyOrNull("ktor.database.hostname")?.getString() ?: "localhost"
    val port = environment.config.propertyOrNull("ktor.database.port")?.getString()?.toInt() ?: 5432
    val dbname = environment.config.propertyOrNull("ktor.database.dbname")?.getString() ?: "postgres"
    val username = environment.config.propertyOrNull("ktor.database.username")?.getString() ?: "postgres"
    val password = environment.config.propertyOrNull("ktor.database.password")?.getString() ?: "postgres"

    Database.connect(
        "jdbc:postgresql://$hostname:$port/$dbname",
        user = username,
        password = password
    )
}
