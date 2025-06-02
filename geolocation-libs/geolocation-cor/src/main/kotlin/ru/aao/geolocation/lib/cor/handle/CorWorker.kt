package ru.aao.geolocation.lib.cor.handle

import ru.aao.geolocation.lib.cor.CorDslMarker
import ru.aao.geolocation.lib.cor.ICorExec
import ru.aao.geolocation.lib.cor.ICorWorkerDsl
import kotlin.reflect.full.staticFunctions

class CorWorker<T> (
    description: String = "",
    active: suspend T.() -> Boolean = { true },
    private val blockHandle: suspend T.() -> Unit = {},
    except: suspend T.(Throwable) -> Unit = {}
): AbstractCorExec<T>(description, active, except) {
    override suspend fun handle(context: T) = blockHandle(context)
}

@CorDslMarker
class CorWorkerDsl<T>: CorExecDsl<T>(), ICorWorkerDsl<T> {
    private var blockHandle: suspend T.() -> Unit = {}
    override fun handle(function: suspend T.() -> Unit) {
        blockHandle = function
    }

    override fun build(): ICorExec<T> = CorWorker(
        description = description,
        active = active,
        blockHandle = blockHandle,
        except = except
    )
}