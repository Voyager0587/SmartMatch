package com.example.smartmatch.base
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapterHelper<B : ViewDataBinding> {

    inner class ViewHolder(val binding: B) : RecyclerView.ViewHolder(binding.root)

    fun createBindingViewHolder(itemLayoutId: Int, parent: ViewGroup?, attachToParent: Boolean): ViewHolder {
        val binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(parent?.context),
            itemLayoutId,
            parent,
            attachToParent
        )
        return ViewHolder(binding)
    }
}