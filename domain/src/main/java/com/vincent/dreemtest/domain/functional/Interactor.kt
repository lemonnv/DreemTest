package com.vincent.dreemtest.domain.functional

import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

typealias ResultHandler<T> = (Result<T>) -> Unit

abstract class SynchronousInteractor<T, in Params>: KoinComponent {

    abstract fun runOnForeground(params: Params): T

    fun execute(params: Params): Result<T> {
        return try {
            Result.success(runOnForeground(params))
        } catch (e: Exception) {
            Result.failure<T>(e)
        }
    }

}

fun <T> SynchronousInteractor<T, Unit>.execute(): Result<T> = execute(Unit)

abstract class Interactor<T, Params>: KoinComponent {

    private val backgroundContext: CoroutineContext = Dispatchers.IO
    private val foregroundContext: CoroutineContext = Dispatchers.Main
    var context: CoroutineContext = foregroundContext

    private var job: Job? = null

    protected abstract suspend fun runOnBackground(params: Params): T

    fun execute(params: Params, handler: ResultHandler<T>) {
        cancel()
        job = CoroutineScope(context).launch {
            try {
                val value = withContext(backgroundContext) {
                    runOnBackground(params)
                }
                Result.success(value)
            } catch (_: CancellationException) {
                return@launch
            } catch (e: Exception) {
                Result.failure<T>(e)
            }.apply {
                handler.invoke(this)
            }
        }
    }

    fun cancel() {
        job?.cancel()
    }

}

fun <T> Interactor<T, Unit>.execute(handler: ResultHandler<T>) {
    execute(Unit, handler)
}
