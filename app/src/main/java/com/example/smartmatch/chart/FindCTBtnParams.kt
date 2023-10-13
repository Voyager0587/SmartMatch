package com.example.smartmatch.chart

import android.view.View
import android.widget.TextView

class FindCTBtnParams<V : View> {
    var id: Int = 0
    var l: Int = 0
    var view: V? = null
    var tv: TextView? = null
    var rawX: Float = 0f
    var rawY: Float = 0f
    var height: Float = 0f
    var width: Float = 0f
    var START_CID: Int = 1
    var END_CID: Int = 0

    override fun toString(): String {
        return "ViewParams{" +
                "view=" + view +
                ", id=" + id +
                ", rawX=" + rawX +
                ", rawY=" + rawY +
                ", height=" + height +
                ", width=" + width +
                '}'
    }
}