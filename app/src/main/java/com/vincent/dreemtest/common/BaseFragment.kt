package com.vincent.dreemtest.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseFragment: Fragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @MainThread
    protected inline fun <T> LiveData<T>.observe(crossinline onChanged: (T) -> Unit): Observer<T> {
        val wrappedObserver = Observer<T> { t -> onChanged.invoke(t) }
        observe(viewLifecycleOwner, wrappedObserver)
        return wrappedObserver
    }
}