package ru.aao.geolocation.lib.cor.handle

import ru.aao.geolocation.lib.cor.CorDslMarker
import ru.aao.geolocation.lib.cor.ICorExec
import ru.aao.geolocation.lib.cor.ICorWorkerDsl

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
    private val handle: suspend T.() -> Unit = {}
    override fun handle(function: suspend T.() -> Unit) {
        handle(function)
    }

    override fun build(): ICorExec<T> = CorWorker(
        description = description,
        active = active,
        blockHandle = handle,
        except = except
    )
}