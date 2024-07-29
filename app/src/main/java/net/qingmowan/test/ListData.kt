package net.qingmowan.test

import androidx.recyclerview.widget.RecyclerView

sealed class ListState {
    data object Flash : ListState()
    class Add(
        val index: Int,
        val range: Int = 1
    ) : ListState()

    class Remove(
        val index: Int,
        val range: Int = 1
    ) : ListState()

    class Update(
        val index: Int,
        val range: Int = 1
    ) : ListState()

    companion object {
        fun doChange(
            adapter: RecyclerView.Adapter<*>,
            listState: ListState
        ) {
            when (listState) {
                is Add -> adapter.notifyItemRangeInserted(listState.index, listState.range)
                //noinspection notifyDataSetChanged
                is Flash -> adapter.notifyDataSetChanged()
                is Remove -> adapter.notifyItemRangeRemoved(listState.index, listState.range)
                is Update -> adapter.notifyItemRangeChanged(listState.index, listState.range, false)
            }
        }
    }
}