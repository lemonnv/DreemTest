package com.vincent.dreemtest.dashboard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vincent.dreemtest.common.BaseFragment

import com.vincent.dreemtest.R
import com.vincent.dreemtest.common.adapter.DataBindingAdapter
import kotlinx.android.synthetic.main.fragment_dashbaord.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<DashboardViewModel>(),
    DataBindingAdapter.OnItemClickListener<NightCardViewModel> {

    override val layoutId: Int = R.layout.fragment_dashbaord

    override val viewModel: DashboardViewModel by viewModel()

    private val adapter = DashboardAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutRefresh.setColorSchemeResources(R.color.primary100, R.color.primary300, R.color.primary500)
        layoutRefresh.setOnRefreshListener {
            viewModel.load()
        }
        viewModel.state.observe {
            layoutRefresh.isRefreshing = it is DashboardViewModel.State.Loading
        }

        viewModel.intent.observe {
            when (it) {
                is DashboardViewModel.Intent.ShowNightDetails -> findNavController().navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToNightFragment(it.night)
                )
                is DashboardViewModel.Intent.ShowToast -> Toast.makeText(
                    context,
                    it.error.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        recyclerView.adapter = adapter
        viewModel.nights.observe { nights ->
            adapter.submitList(nights.map { it.toCardViewModel() })
        }
        viewModel.load()
    }

    override fun onItemClick(item: NightCardViewModel) {
        viewModel.select(item.id)
    }

}