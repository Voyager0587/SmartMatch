package com.example.smartmatch.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.smartmatch.R
import com.example.smartmatch.base.kxt.toast

/**
 * @className: MultiButton
 * @author: Voyager
 * @description: 多选按钮自定义View
 * @date:  2023/10/3 20:30
 * @version 1.0
 **/
@SuppressLint("MissingInflatedId")
class MultiButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var name: TextView
    var layout: RelativeLayout
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

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_multi_layout, this)
        name = view.findViewById(R.id.tv_name)
        layout = view.findViewById(R.id.rl_item)
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
        context.toast("点击item")
        judge = !judge
        val backgroundResource =
            if (judge) R.drawable.round_20 else R.drawable.ic_launcher_background
        layout.setBackgroundResource(backgroundResource)
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.clickListener = listener
    }

}