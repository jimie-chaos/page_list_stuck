package net.qingmowan.nonresidential.activities.deviceRepairManage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import net.qingmowan.test.DeviceRepairListFragment

class DeviceRepairPageAdapter(
    val data: MutableList<DeviceRepairPageItem>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return DeviceRepairListFragment.newInstance(data[position].state)
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }
}

data class DeviceRepairPageItem(
    val state: Int,
    val tasks: MutableList<String>,
    var isLoading: Boolean,
    var isFullyLoaded: Boolean,
    val pageSize: Int,
    var pageNumber: Int
)

