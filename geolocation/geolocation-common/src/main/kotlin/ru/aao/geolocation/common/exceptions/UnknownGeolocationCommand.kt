package ru.aao.geolocation.common.exceptions

import ru.aao.geolocation.common.models.GlCommand

class UnknownGeolocationCommand(command: GlCommand): Throwable("Wrong command $command at mapping toTransport stage") {
}