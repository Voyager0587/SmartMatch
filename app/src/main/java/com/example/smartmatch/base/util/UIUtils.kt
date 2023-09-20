package com.example.base.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.util.TypedValue
import java.text.SimpleDateFormat
import java.util.*


object UIUtils {
    fun dpToPx(dp: Float): Int =
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        ).toInt()

    fun getScreenW(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    @SuppressLint("SimpleDateFormat")
    fun stampToDate(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val lt: Long = s.toLong()
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }
}