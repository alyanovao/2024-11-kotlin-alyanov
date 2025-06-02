package ru.aao.geolocation.lib.cor.handle

import ru.aao.geolocation.lib.cor.ICorExec
import ru.aao.geolocation.lib.cor.ICorExecDsl

abstract class AbstractCorExec<T>(
    override val description: String,
    private val blockActive: suspend T.() -> Boolean = { true },
    private val blockExcept: suspend T.(Throwable) -> Unit = {}
): ICorExec<T> {
    protected abstract suspend fun handle(context: T)

    private suspend fun active(context: T): Boolean = context.blockActive()
    private suspend fun except(context: T, e: Throwable) = context.blockExcept(e)


    override suspend fun exec(context: T) {
        if (blockActive(context)) {
            try {
                handle(context)
            }
            catch (e: Throwable) {
                blockExcept(context, e)
            }
        }
    }
}

abstract class CorExecDsl<T>: ICorExecDsl<T> {
    override var title: String = ""
    override var description: String = ""
    protected var active: suspend T.() -> Boolean = { true }
    protected var except: suspend T.(Throwable) -> Unit = {e: Throwable -> throw e}

    override fun active(function: suspend T.() -> Boolean) {
        active = function
    }

    override fun except(function: suspend T.(e: Throwable) -> Unit) {
        except = function
    }
}