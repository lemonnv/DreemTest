package com.vincent.dreemtest.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.vincent.dreemtest.common.BaseFragment

import com.vincent.dreemtest.R
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment<SplashViewModel>() {

    override val layoutId: Int = R.layout.fragment_splash

    override val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.intent.observe {
            when (it) {
                SplashViewModel.Intent.ShowDashboard -> SplashFragmentDirections.actionSplashFragmentToNavDashboard()
                else -> null
            }?.apply {
                findNavController().navigate(this)
            }
        }
    }

}