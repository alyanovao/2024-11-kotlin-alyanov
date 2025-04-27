package ru.aao.geolocation.app.ktor

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.rest(appSettings: GlAppSetting) {
    route("") {
        post ("create") {
            call.createGeolocation(appSettings)
        }

        post ("read/current") {
            call.readCurrentGeolocation(appSettings)
        }

        post ("read/all") {
            call.readAllGeolocation(appSettings)
        }

        post ("update") {
            call.updateGeolocation(appSettings)
        }

        post ("delete") {
            call.deleteGeolocation(appSettings)
        }
    }
}