package com.vincent.dreemtest.dashboard.night

import com.vincent.dreemtest.common.BaseDataBindingFragment
import com.vincent.dreemtest.databinding.FragmentNightBinding
import org.koin.android.ext.android.inject

class NightFragment: BaseDataBindingFragment<NightViewModel, FragmentNightBinding>() {

    override val layoutId: Int = com.vincent.dreemtest.R.layout.fragment_night

    override val viewModel: NightViewModel by inject()

}