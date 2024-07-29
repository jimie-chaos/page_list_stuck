package net.qingmowan.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.qingmowan.test.ContextExtension.obtainViewModel

class DeviceRepairListFragment : Fragment(), LifecycleEventObserver {
    var state: Int = -1
    lateinit var list: RecyclerView
    val viewModel by lazy { requireActivity().obtainViewModel(DeviceRepairManageViewModel::class.java) }

    private fun onImageClick(id: String) {
        findNavController().navigate(R.id.action_deviceRepairPageFragment_to_showFragment)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!requireArguments().containsKey("state")) throw RuntimeException("未知维修状态")
        state = requireArguments().getInt("state")
        lifecycle.addObserver(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_device_repair_list, container, false)
        list = view.findViewById(R.id.list)
        val adapter = DeviceRepairAdapter(this::onImageClick)
        adapter.data = viewModel.repairTasks.find { it.state == state }!!.tasks
        list.adapter = adapter
        viewModel.listLive.observe(viewLifecycleOwner) { (state, listState) ->
            if (state == this.state) {
                ListState.doChange(adapter, listState)
            }
        }
        list.addOnScrollListener(onScroll)
        return view
    }

    private val onScroll by lazy {
        object : RecyclerView.OnScrollListener() {
            val layoutManager = list.layoutManager as LinearLayoutManager
            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                if (layoutManager.itemCount - layoutManager.findLastVisibleItemPosition() < 5) {
                    viewModel.loadRepairTaskByState(state)
                }
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(state: Int) = DeviceRepairListFragment().apply {
            arguments = Bundle().apply {
                putInt("state", state)
            }
        }
    }

    override fun onStateChanged(
        source: LifecycleOwner,
        event: Lifecycle.Event
    ) {
        when (event) {
            Lifecycle.Event.ON_CREATE,
            Lifecycle.Event.ON_START,
            Lifecycle.Event.ON_RESUME,
            Lifecycle.Event.ON_PAUSE,
            Lifecycle.Event.ON_STOP,
            Lifecycle.Event.ON_DESTROY,
            Lifecycle.Event.ON_ANY -> {
            }
        }
    }
}