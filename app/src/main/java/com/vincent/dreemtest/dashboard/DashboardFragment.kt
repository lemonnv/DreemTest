package com.vincent.dreemtest.dashboard

import android.os.Bundle
import android.view.View
import com.vincent.dreemtest.common.BaseFragment

import com.vincent.dreemtest.R
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_dashbaord

    private val viewModel: DashboardViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}