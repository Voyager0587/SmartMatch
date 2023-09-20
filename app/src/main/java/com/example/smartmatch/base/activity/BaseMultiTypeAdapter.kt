package com.example.smartmatch.base.activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.smartmatch.R
import java.util.*


abstract class BaseMultiTypeAdapter<T> :
    RecyclerView.Adapter<BaseMultiTypeAdapter.MultiTypeViewHolder>() {

    private var mData: List<T> = mutableListOf()

    fun setData(data: List<T>?) {
        data?.let {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mData.size
                }

                override fun getNewListSize(): Int {
                    return it.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldData: T = mData[oldItemPosition]
                    val newData: T = it[newItemPosition]
                    return this@BaseMultiTypeAdapter.areItemsTheSame(oldData, newData)
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val oldData: T = mData[oldItemPosition]
                    val newData: T = it[newItemPosition]
                    return this@BaseMultiTypeAdapter.areItemContentsTheSame(
                        oldData,
                        newData,
                        oldItemPosition,
                        newItemPosition
                    )
                }
            })
            mData = data
            result.dispatchUpdatesTo(this)
        } ?: let {
            mData = mutableListOf()
            notifyItemRangeChanged(0, mData.size)
        }

    }

    fun addData(data: List<T>?, position: Int? = null) {
        if (data.isNullOrEmpty()) return
        with(LinkedList(mData)) {
            position?.let {
                val startPosition = when {
                    it < 0 -> 0
                    it >= size -> size
                    else -> it
                }
                addAll(startPosition, data)
            } ?: addAll(data)
            setData(this)
        }
    }

    protected open fun areItemContentsTheSame(
        oldItem: T,
        newItem: T,
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean =
        oldItem == newItem

    protected open fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    fun getData(): List<T> = mData

    fun getItem(position: Int): T = mData[position]

    fun getActualPosition(data: T): Int = mData.indexOf(data)

    override fun getItemCount(): Int = mData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiTypeViewHolder =
        MultiTypeViewHolder(onCreateMultiViewHolder(parent, viewType))

    override fun onBindViewHolder(holder: MultiTypeViewHolder, position: Int) {
        holder.onBindViewHolder(holder, getItem(position), position)
        holder.binding.executePendingBindings()
    }

    override fun onViewAttachedToWindow(holder: MultiTypeViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.apply {
            clearAnimation()
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_in_scroll))
        }
    }

    abstract fun MultiTypeViewHolder.onBindViewHolder(
        holder: MultiTypeViewHolder,
        item: T,
        position: Int
    )

    abstract fun onCreateMultiViewHolder(parent: ViewGroup, viewType: Int): ViewDataBinding

    protected fun <VB : ViewDataBinding> loadLayout(vbClass: Class<VB>, parent: ViewGroup): VB {
        val inflate = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return inflate.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
    }

    class MultiTypeViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}