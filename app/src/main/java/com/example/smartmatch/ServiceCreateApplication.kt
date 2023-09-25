package com.example.smartmatch

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.smartmatch.base.kxt.initSp
import com.example.smartmatch.ui.dialogInputInfo
import com.example.smartmatch.ui.dialogMessageInfo
import com.example.smartmatch.ui.dialogOkInfo
import com.example.smartmatch.ui.dialogTitleInfo
import com.kongzue.dialogx.DialogX
import com.kongzue.dialogx.dialogs.MessageDialog
import com.kongzue.dialogx.style.MaterialStyle


/**
 * @className: ServiceCreateApplication
 * @author: Voyager
 * @description: TODO
 * @date:  2023/9/23 11:14
 * @version 1.0
 **/
class ServiceCreateApplication:Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initDialog()

    }
    private fun initDialog() {
        DialogX.init(this)
        DialogX.globalStyle = MaterialStyle.style()
        DialogX.titleTextInfo = dialogTitleInfo(this)
        DialogX.okButtonTextInfo = dialogOkInfo(this)
        DialogX.messageTextInfo = dialogMessageInfo(this)
        DialogX.inputInfo = dialogInputInfo(this)
        DialogX.implIMPLMode = DialogX.IMPL_MODE.DIALOG_FRAGMENT
        DialogX.DEBUGMODE = true

        MessageDialog.overrideEnterAnimRes = com.kongzue.dialogx.R.anim.anim_dialogx_bottom_enter
        MessageDialog.overrideExitAnimRes = com.kongzue.dialogx.R.anim.anim_dialogx_bottom_exit

    }
}