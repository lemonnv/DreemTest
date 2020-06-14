package com.vincent.dreemtest.dashboard

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.vincent.dreemtest.common.BaseFragment

import com.vincent.dreemtest.R
import com.vincent.dreemtest.common.adapter.DataBindingAdapter
import kotlinx.android.synthetic.main.fragment_dashbaord.*
import org.koin.android.viewmodel.ext.android.viewModel

class DashboardFragment: BaseFragment<DashboardViewModel>(), DataBindingAdapter.OnItemClickListener<NightCardViewModel> {

    override val layoutId: Int = R.layout.fragment_dashbaord

    override val viewModel: DashboardViewModel by viewModel()

    private val adapter = Adapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutRefresh.setOnRefreshListener {
            viewModel.load()
        }
        viewModel.state.observe {
            layoutRefresh.isRefreshing = it is DashboardViewModel.State.Loading
        }

        recyclerView.adapter = adapter
        viewModel.load()
        viewModel.nights.observe { nights ->
            adapter.submitList(nights.map { it.toCardViewModel() })
        }

    }

    override fun onItemClick(item: NightCardViewModel) {

    }

    private class Adapter(listener: OnItemClickListener<NightCardViewModel>): DataBindingAdapter<NightCardViewModel>(DiffCallback(), listener) {

        class DiffCallback: DiffUtil.ItemCallback<NightCardViewModel>() {

            override fun areItemsTheSame(oldItem: NightCardViewModel, newItem: NightCardViewModel): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: NightCardViewModel, newItem: NightCardViewModel): Boolean = true

        }

        override fun getItemViewType(position: Int): Int = R.layout.item_night

    }

}