package com.example.smartmatch.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.smartmatch.R
import com.example.smartmatch.base.kxt.toast

/**
 * @className: ItemButton
 * @author: Voyager
 * @description: 自定义View（实力欠缺，有能力的看看纯自定义个试试？）
 * @date:  2023/9/26 22:52
 * @version 1.0
 **/
class ItemButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs){
    lateinit var name:TextView
    lateinit var info:String
    lateinit var rl: RelativeLayout
    var net_id:Int =-1
    interface OnClickListener {
        fun onTitleClick()
    }
    private var clickListener: OnClickListener? = null

    fun text(info:String){
        this.info = info
        name.text=info
    }
    init {
        val view=LayoutInflater.from(context).inflate(R.layout.item_layout, this)
        name=view.findViewById(R.id.name)
        rl=view.findViewById(R.id.rl_item)
        name.setOnClickListener {
            clickListener?.onTitleClick()
            toast("Choose:$info")
        }
        rl.setOnClickListener{
            clickListener?.onTitleClick()
            toast("Choose:$info")
        }
//        rl.setOnClickListener{
//            clickListener?.onTitleClick()
//        }
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

}