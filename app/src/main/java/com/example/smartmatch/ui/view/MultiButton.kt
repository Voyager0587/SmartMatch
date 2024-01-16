package com.example.smartmatch.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.smartmatch.R

/**
 * @className: MultiButton
 * @author: Voyager
 * @description: 多选按钮自定义View（赶时间所以写的不怎么样(其实就是不怎么会哈哈哈)）
 * @date:  2023/10/3 20:30
 * @version 1.0
 **/
@SuppressLint("MissingInflatedId")
class MultiButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var name: TextView
    var percent:TextView
    var layout: RelativeLayout
    var scene_id:Int=-1
    var net_id:Int=-1
    var item_id:Int=-1
    lateinit var info: String
        private set
    var judge = false

    private var clickListener: OnClickListener? = null

    interface OnClickListener {
        fun onTitleClick()
    }

    fun text(info: String) {
        this.info = info
        name.text = info
    }

    /**
     *
     * @param info
     * @param _percent
     * @param _scene_id
     */
    fun text(info: String,_percent: Double,_scene_id:Int) {
        text(info)
        percent.text = _percent.toString()
        if (_percent<0)
            percent.text=""
        scene_id=_scene_id
    }
    fun percent(_percent: Double){
        percent.text = _percent.toString()
    }
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_multi_layout, this)
        name = view.findViewById(R.id.tv_name)
        layout = view.findViewById(R.id.rl_item)
        percent=view.findViewById(R.id.tv_percent)
        layout.setOnClickListener {
            toggleBackground()
            clickListener?.onTitleClick()
        }
        name.setOnClickListener {
            toggleBackground()
            clickListener?.onTitleClick()
        }

    }

    private fun toggleBackground() {
        judge = !judge
        val backgroundResource =
            if (judge) R.drawable.round_20_selected else R.drawable.round_20
        layout.setBackgroundResource(backgroundResource)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

}