package net.qingmowan.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.qingmowan.nonresidential.activities.deviceRepairManage.adapter.DeviceRepairPageItem

class DeviceRepairManageViewModel : ViewModel() {
    val repairTasks: MutableList<DeviceRepairPageItem> = mutableListOf()

    init {
        for (i in 1..5) {
            repairTasks.add(DeviceRepairPageItem(i, mutableListOf(), isLoading = false, isFullyLoaded = false, pageSize = 10, pageNumber = 1))
        }
    }

    val title = mutableMapOf(
        1 to "yi", 2 to "er", 3 to "san", 4 to "si", 5 to "wu"
    )
    private val _listLive: MutableLiveData<Pair<Int, ListState>> = MutableLiveData()
    val listLive: LiveData<Pair<Int, ListState>> = _listLive

    fun loadRepairTaskByState(state: Int) {
        val pageItem = repairTasks.find { it.state == state } ?: return
        if (pageItem.isLoading) return
        if (pageItem.isFullyLoaded) return
        pageItem.isLoading = true
        val start = pageItem.tasks.size
        pageItem.pageSize.rangeTo(0).map { it }
        val data = start.rangeTo(pageItem.pageSize * pageItem.pageNumber).map { it.toString() }
        pageItem.tasks.addAll(data)
        _listLive.postValue(state to ListState.Add(start, data.size))
        pageItem.pageNumber++
        pageItem.isLoading = false
    }
}