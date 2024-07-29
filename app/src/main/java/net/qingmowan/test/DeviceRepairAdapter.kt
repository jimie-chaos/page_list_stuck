package net.qingmowan.test

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DeviceRepairAdapter(val onItemClick: (String) -> Unit) : RecyclerView.Adapter<DeviceRepairHolder>() {
    lateinit var data: List<String>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceRepairHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_repair, parent, false) as TextView
        return DeviceRepairHolder(view).apply {
            textView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(
        holder: DeviceRepairHolder,
        position: Int
    ) {
        holder.item = data[position]
        holder.textView.text = holder.item
    }

    enum class ClickType {
        VIEW, TRANSFER, COMPLETE_TASK, CHECK_TASK
    }

}

class DeviceRepairHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
    lateinit var item: String
}