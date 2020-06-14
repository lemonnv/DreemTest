package com.vincent.dreemtest.dashboard.night

import com.vincent.dreemtest.common.BaseDataBindingFragment
import com.vincent.dreemtest.databinding.FragmentNightBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NightFragment: BaseDataBindingFragment<NightViewModel, FragmentNightBinding>() {

    override val layoutId: Int = com.vincent.dreemtest.R.layout.fragment_night

    override val viewModel: NightViewModel by viewModel {
        parametersOf(arguments?.let { NightFragmentArgs.fromBundle(it).night })
    }

}