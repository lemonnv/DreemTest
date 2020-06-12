package com.vincent.dreemtest.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vincent.dreemtest.domain.functional.Interactor
import com.vincent.dreemtest.domain.functional.ResultHandler
import com.vincent.dreemtest.domain.functional.execute
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected fun <T, Params> Interactor<T, Params>.executeInViewModelScope(
        params: Params,
        handler: ResultHandler<T>
    ) {
        context = viewModelScope.coroutineContext
        return execute(params, handler)
    }

    protected fun <T> Interactor<T, Unit>.executeInViewModelScope(handler: ResultHandler<T>) {
        context = viewModelScope.coroutineContext
        return execute(handler)
    }

}