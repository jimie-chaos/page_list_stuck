package net.qingmowan.test

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object ContextExtension {
    fun <T : ViewModel> ViewModelStoreOwner.obtainViewModel(clazz: Class<T>): T {
        return ViewModelProvider(this)[clazz]
    }
}