package exceptions

import models.GlCommand

class UnknownGeolocationCommand(command: GlCommand): Throwable("Wrong command $command at mapping toTransport stage") {
}