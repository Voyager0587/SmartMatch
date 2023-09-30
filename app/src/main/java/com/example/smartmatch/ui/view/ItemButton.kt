package com.example.smartmatch.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.smartmatch.R
import com.example.smartmatch.base.kxt.toast

/**
 * @className: ItemButton
 * @author: Voyager
 * @description: 自定义View
 * @date:  2023/9/26 22:52
 * @version 1.0
 **/
class ItemButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs){
    lateinit var name:TextView
    lateinit var info:String
    interface OnClickListener {
        fun onTitleClick()
    }
    private var clickListener: OnClickListener? = null

    fun text(info:String){
        this.info = info
        name.text=info
    }
    init {
        var view=LayoutInflater.from(context).inflate(R.layout.item_layout, this)
        name=view.findViewById(R.id.name)
        name.setOnClickListener {
           context.toast("点击item")
            clickListener?.onTitleClick()
        }
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

}