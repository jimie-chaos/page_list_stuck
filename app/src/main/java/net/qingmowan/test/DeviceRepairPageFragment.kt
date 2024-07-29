package net.qingmowan.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import net.qingmowan.nonresidential.activities.deviceRepairManage.adapter.DeviceRepairPageAdapter
import net.qingmowan.test.ContextExtension.obtainViewModel
import net.qingmowan.test.databinding.FragmentDeviceRepairPageBinding

class DeviceRepairPageFragment : Fragment() {
    private val viewModel by lazy { requireActivity().obtainViewModel(DeviceRepairManageViewModel::class.java) }
    private lateinit var binding: FragmentDeviceRepairPageBinding
    private lateinit var adapter: DeviceRepairPageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            Log.w("TAG", "event: ${event.name}")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = DeviceRepairPageAdapter(viewModel.repairTasks, this)
        binding = FragmentDeviceRepairPageBinding.inflate(layoutInflater, container, false)
        viewModel.listLive.observe(viewLifecycleOwner, this::onListChange)
        binding.viewPager.registerOnPageChangeCallback(onPageChangeListener)
        binding.topAppBar.setNavigationOnClickListener { requireActivity().finish() }
        binding.viewPager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(binding.topTabBar, binding.viewPager) { tab, position ->
            tab.setText(viewModel.title[viewModel.repairTasks[position].state])
        }
        tabLayoutMediator.attach()
        adapter.notifyDataSetChanged()
        return binding.root
    }

    private fun onListChange(pair: Pair<Int, ListState>) {
        val deviceRepairPageItem = viewModel.repairTasks[binding.viewPager.currentItem]
        val isCurrent = deviceRepairPageItem.state == pair.first
        if (isCurrent && deviceRepairPageItem.tasks.isEmpty()) {
            binding.empty.visibility = View.VISIBLE
        }

    }


    private val onPageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Log.e("TAG", "onPageScrollStateChanged: $position")
            val pageItem = viewModel.repairTasks[position]
            if (pageItem.tasks.isEmpty() && !pageItem.isFullyLoaded) {
                viewModel.loadRepairTaskByState(pageItem.state)
            }
            binding.empty.visibility = if (pageItem.tasks.isEmpty() && pageItem.isFullyLoaded) View.VISIBLE else View.GONE
        }
    }


}
