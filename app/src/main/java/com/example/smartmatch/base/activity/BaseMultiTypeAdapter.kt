package com.example.smartmatch.base.activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.smartmatch.R
import java.util.*


/**
 * 基本的多类型适配器
 */
abstract class BaseMultiTypeAdapter<T> :
    RecyclerView.Adapter<BaseMultiTypeAdapter.MultiTypeViewHolder>() {

    private var mData: List<T> = mutableListOf()

    /**
     * 设置数据
     * @param data 数据列表
     */
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

    /**
     * 添加数据
     * @param data 数据列表
     * @param position 位置
     */
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

    /**
     * 判断内容是否相同
     * @param oldItem 旧项
     * @param newItem 新项
     * @param oldItemPosition 旧项位置
     * @param newItemPosition 新项位置
     * @return 内容是否相同
     */
    protected open fun areItemContentsTheSame(
        oldItem: T,
        newItem: T,
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean =
        oldItem == newItem

    /**
     * 判断项是否相同
     * @param oldItem 旧项
     * @param newItem 新项
     * @return 项是否相同
     */
    protected open fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    /**
     * 获取数据列表
     * @return 数据列表
     */
    fun getData(): List<T> = mData

    /**
     * 获取指定位置的项
     * @param position 位置
     * @return 项
     */
    fun getItem(position: Int): T = mData[position]

    /**
     * 获取实际位置
     * @param data 项
     * @return 实际位置
     */
    fun getActualPosition(data: T): Int = mData.indexOf(data)

    /**
     * 获取列表大小
     * @return 列表大小
     */
    override fun getItemCount(): Int = mData.size

    /**
     * 创建新的视图持有人
     * @param parent 父视图组
     * @param viewType 视图类型
     * @return 新的视图持有人
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiTypeViewHolder =
        MultiTypeViewHolder(onCreateMultiViewHolder(parent, viewType))

    /**
     * 绑定视图持有人
     * @param holder 视图持有人
     * @param position 位置
     */
    override fun onBindViewHolder(holder: MultiTypeViewHolder, position: Int) {
        holder.onBindViewHolder(holder, getItem(position), position)
        holder.binding.executePendingBindings()
    }

    /**
     * 视图与窗口附加
     * @param holder 视图持有人
     */
    override fun onViewAttachedToWindow(holder: MultiTypeViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.apply {
            clearAnimation()
            startAnimation(AnimationUtils.loadAnimation(context, R.anim.scale_in_scroll))
        }
    }

    /**
     * 创建视图持有人的布局
     * @param holder 视图持有人
     * @param item 项
     * @param position 位置
     * @return 视图持有人的布局
     */
    abstract fun MultiTypeViewHolder.onBindViewHolder(
        holder: MultiTypeViewHolder,
        item: T,
        position: Int
    )

    /**
     * 创建视图持有人的布局
     * @param parent 父视图组
     * @param viewType 视图类型
     * @return 视图持有人的布局
     */
    abstract fun onCreateMultiViewHolder(parent: ViewGroup, viewType: Int): ViewDataBinding

    /**
     * 加载布局
     * @param vbClass 布局类
     * @param parent 父视图组
     * @return 布局
     */
    protected fun <VB : ViewDataBinding> loadLayout(vbClass: Class<VB>, parent: ViewGroup): VB {
        val inflate = vbClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return inflate.invoke(null, LayoutInflater.from(parent.context), parent, false) as VB
    }

    /**
     * 视图持有人
     */
    class MultiTypeViewHolder(var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)
}
