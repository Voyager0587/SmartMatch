package com.example.smartmatch.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.smartmatch.R

/**
 * @className: ItemButton
 * @author: Voyager
 * @description: TODO
 * @date:  2023/9/26 22:52
 * @version 1.0
 **/
class ItemButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs){
    lateinit var name:TextView
    interface OnClickListener {
        fun onTitleClick()
    }
    private var clickListener: OnClickListener? = null

    fun text(info:String){
        name.text=info
    }
    init {
        var view=LayoutInflater.from(context).inflate(R.layout.item_layout, this)
        name=view.findViewById(R.id.name)
        name.setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
            // 触发点击事件回调
            clickListener?.onTitleClick()
        }
    }

    // 设置点击事件监听器
    fun setOnClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

}