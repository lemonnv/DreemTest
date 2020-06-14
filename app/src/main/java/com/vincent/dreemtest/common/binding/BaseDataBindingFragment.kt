package com.vincent.dreemtest.common.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.vincent.dreemtest.BR
import com.vincent.dreemtest.common.BaseFragment

abstract class BaseDataBindingFragment<ViewModel : androidx.lifecycle.ViewModel, DataBinding : ViewDataBinding> :
    BaseFragment<ViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<DataBinding>(
            inflater,
            layoutId,
            container,
            false
        ).apply {
            onCreateViewDataBinding(this)
        }.root
    }

    open fun onCreateViewDataBinding(dataBinding: DataBinding) {
        dataBinding.lifecycleOwner = this
        dataBinding.setVariable(BR.viewModel, viewModel)
    }

}
