import ru.aao.geolocation.biz.GlProcessor
import ru.aao.geolocation.common.GlSettings
import ru.aao.geolocation.common.models.GlCommand

abstract class ValidationTest {
    protected abstract val glCommand: GlCommand
    private val settings by lazy { GlSettings() }
    protected val processor by lazy { GlProcessor(settings) }
}