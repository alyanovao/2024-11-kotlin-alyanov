package ru.aao.geolocation.lib.cor.handle

import ru.aao.geolocation.lib.cor.CorDslMarker
import ru.aao.geolocation.lib.cor.ICorChainDsl
import ru.aao.geolocation.lib.cor.ICorExec
import ru.aao.geolocation.lib.cor.ICorExecDsl

class CorChain<T> (
    private val execs: List<ICorExec<T>>,
    description: String = "",
    active: suspend T.() -> Boolean = { true },
    except: suspend T.(Throwable) -> Unit = {}
): AbstractCorExec<T>(description, active, except) {
    override suspend fun handle(context: T) {
        execs.forEach { it.exec(context) }
    }
}

@CorDslMarker
class CorChainDsl<T>(): CorExecDsl<T>(), ICorChainDsl<T> {
    private val workers: MutableList<ICorExecDsl<T>> = mutableListOf()

    override fun add(worker: ICorExecDsl<T>) {
        workers.add(worker)
    }

    override fun build(): ICorExec<T> = CorChain(
        description = description,
        execs = workers.map { it.build() },
        active = active,
        except = except
    )
}
