package com.dev.ldhnam.kotlinsample

import android.support.v7.widget.RecyclerView

abstract class RxRecyclerViewAdapterCallback<T>(private val mAdapter: RecyclerView.Adapter<*>) : RxSortedListCallback<T>() {

    override fun onChanged(position: Int, count: Int) {
        mAdapter.notifyItemChanged(position, count)
    }

    override fun onInserted(position: Int, count: Int) {
        mAdapter.notifyItemRangeInserted(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        mAdapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRemoved(position: Int, count: Int) {
        mAdapter.notifyItemRangeRemoved(position, count)
    }
}

