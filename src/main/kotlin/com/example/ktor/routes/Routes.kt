package com.example.routes

import com.example.data.DataManager
import com.example.data.Order
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Route.routes() {
    val orderManager = DataManager()

    route("/order") {
        get("/") {
            call.respond(orderManager.getAllOrders())
        }

        put("/") {
            val receive = call.receive(Order::class)
            val updateOrder = orderManager.updateOrder(receive)
                ?: return@put call.respond(HttpStatusCode.BadRequest)
            call.respond(updateOrder)
        }

        post("/") {
            val order = call.receive(Order::class)
            orderManager.addOrder(order)
            call.respond(order)
        }

        delete("/{id}") {
            val id: String = call.parameters["id"]
                ?: return@delete call.respond(HttpStatusCode.BadRequest)
            val removeOrder: Order = orderManager.removeOrder(UUID.fromString(id))
            call.respond(removeOrder)
        }
    }
}