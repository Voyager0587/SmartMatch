package com.example.smartmatch.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.smartmatch.R
import com.example.smartmatch.ServiceCreateApplication
import com.example.smartmatch.base.kxt.toast
import com.kongzue.dialogx.util.InputInfo
import com.kongzue.dialogx.util.TextInfo

/**
 * @author:SunShibo
 * @date:2023-03-27 22:54
 * @feature:
 *
 */

private val titleInfo = TextInfo()
private val okInfo = TextInfo()
private val contentInfo = TextInfo()
private val cancelInfo = TextInfo()
private val inputInfo = InputInfo()

internal fun dialogTitleInfo(context: Context): TextInfo {
    titleInfo.fontColor = context.getColor(R.color.main_color_primary_secondary)
    return titleInfo
}


internal fun dialogOkInfo(context: Context): TextInfo {
    okInfo.fontColor = context.getColor(R.color.second_color)
    return okInfo
}
internal fun dialogMessageInfo(context: Context): TextInfo {
    contentInfo.fontColor = context.getColor(R.color.icon_color)
    return contentInfo
}

internal fun dialogCancelInfo(context: Context): TextInfo {
    cancelInfo.fontColor = context.getColor(R.color.teal_200)
    return cancelInfo
}

internal fun dialogInputInfo(context: Context): InputInfo? {
    inputInfo.apply {
        bottomLineColor = context.getColor(R.color.main_color_primary_variant)
        cursorColor = context.getColor(R.color.second_color_variant)
        textInfo = contentInfo
    }
    return inputInfo
}

fun String.toast(){
    ServiceCreateApplication.context.toast(this)
}

enum class DeviceType{
   None, Air, Lamp, DoorLock
}




