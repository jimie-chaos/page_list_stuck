package net.qingmowan.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.qingmowan.test.ContextExtension.obtainViewModel
import net.qingmowan.test.databinding.ActivityDeviceRepairManageBinding

class DeviceRepairManageActivity : AppCompatActivity() {

    val viewModel by lazy { obtainViewModel( DeviceRepairManageViewModel::class.java) }
    val binding by lazy { ActivityDeviceRepairManageBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
        setContentView(binding.root)
    }
}