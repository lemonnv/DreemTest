package com.vincent.dreemtest.dashboard

import androidx.recyclerview.widget.DiffUtil
import com.vincent.dreemtest.R
import com.vincent.dreemtest.common.adapter.DataBindingAdapter

class DashboardAdapter(listener: OnItemClickListener<NightCardViewModel>) :
    DataBindingAdapter<NightCardViewModel>(DiffCallback(), listener) {

    class DiffCallback : DiffUtil.ItemCallback<NightCardViewModel>() {

        override fun areItemsTheSame(
            oldItem: NightCardViewModel,
            newItem: NightCardViewModel
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: NightCardViewModel,
            newItem: NightCardViewModel
        ): Boolean = true

    }

    override fun getItemViewType(position: Int): Int =
        R.layout.item_night

}