import models.GlState
import java.util.Collections.singletonList

open class GlProcessor(val corSettings: GlSettings) {
    suspend fun exec(ctx: GeolocationContext) {
        ctx.glResponse = GeolocationStub.get()
        ctx.glResponseList = singletonList(GeolocationStub.get())
        ctx.state = GlState.RUNNING
    }
}