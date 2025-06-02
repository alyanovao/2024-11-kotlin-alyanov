package ru.aao.geolocation.lib.cor

import ru.aao.geolocation.lib.cor.handle.CorChainDsl
import ru.aao.geolocation.lib.cor.handle.CorWorkerDsl

@CorDslMarker
interface ICorExecDsl<T> {
    var title: String
    var description: String
    fun active(function: suspend T.() -> Boolean)
    fun except(function: suspend T.(e: Throwable) -> Unit)
    fun build(): ICorExec<T>
}

@CorDslMarker
interface ICorChainDsl<T>: ICorExecDsl<T> {
    fun add(worker: ICorExecDsl<T>)
}

@CorDslMarker
interface ICorWorkerDsl<T>: ICorExecDsl<T> {
    fun handle(function: suspend T.() -> Unit)
}

fun <T> rootChain(function: ICorChainDsl<T>.() -> Unit): ICorChainDsl<T> = CorChainDsl<T>().apply(function)

fun <T> ICorChainDsl<T>.chain(function: ICorChainDsl<T>.() -> Unit) {
    add(CorChainDsl<T>().apply(function))
}

fun <T> ICorChainDsl<T>.worker(function: ICorWorkerDsl<T>.() -> Unit) {
    add(CorWorkerDsl<T>().apply(function))
}

fun <T> ICorChainDsl<T>.worker(
    description: String,
    blockHandle: T.() -> Unit
) {
    add(CorWorkerDsl<T>().also {
        it.title = title
        it.description = description
        it.handle(blockHandle)
    })
}