package ru.aao.geolocation.biz

import GeolocationStub
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.GlState
import java.util.Collections.singletonList

open class GlProcessor(val corSettings: GlSettings) {
    suspend fun exec(ctx: GeolocationContext) {
        ctx.glResponse = GeolocationStub.get()
        ctx.glResponseList = singletonList(GeolocationStub.get())
        ctx.state = GlState.RUNNING
    }
}