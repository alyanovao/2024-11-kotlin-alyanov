import kotlinx.coroutines.test.runTest
import ru.aao.geolocation.biz.GlProcessor
import ru.aao.geolocation.common.GeolocationContext
import ru.aao.geolocation.common.models.GlCommand
import ru.aao.geolocation.common.models.GlState
import ru.aao.geolocation.common.models.GlWorkMode
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidationCreateTest: ValidationTest() {
    override val glCommand: GlCommand = GlCommand.CREATE

    @Test
    fun test() = runTest {
        exchange(glCommand, processor)
    }

    fun exchange(command: GlCommand, processor: GlProcessor) = runTest {
        val ctx = GeolocationContext(
            command = command,
            state = GlState.NONE,
            workMode = GlWorkMode.TEST
        )
        processor.exec(ctx)
        //assertEquals(0, ctx.errors.size)
    }
}