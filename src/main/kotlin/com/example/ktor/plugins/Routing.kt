package com.example.ktor.plugins

import com.example.routes.routes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {

        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError)
            throw cause
        }

    }
    routing {
        routes()
        get("/") {
            call.respondText("Hello World!")
        }

        static("/static") {
            resources("static")
        }
    }
}
