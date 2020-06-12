package com.vincent.dreemtest.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.vincent.dreemtest.common.BaseFragment

import com.vincent.dreemtest.R
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment() {

    override val layoutId: Int = R.layout.fragment_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.intent.observe {
            when (it) {
                SplashViewModel.Intent.ShowDashboard -> SplashFragmentDirections.actionSplashFragmentToNavDashboard()
                else -> null
            }?.run {
                findNavController().navigate(this)
            }
        }
    }

}