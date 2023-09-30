package com.example.base.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.smartmatch.base.kxt.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * @author:Voyager
 * @date:2023 9 20
 * @feature:Adjust the system view status
 * @description：调整系统视图状态
 */
object StatusUtil {

    /**
     * 用于初始化Fragment的状态栏样式,设置透明状态栏,隐藏状态栏内容。
     */
    @RequiresApi(Build.VERSION_CODES.P)
    fun initFragmentBar(context: Fragment, isImmerse: Boolean) {
        context.apply {
            requireActivity().window.statusBarColor = Color.TRANSPARENT

            val controller = WindowInsetsControllerCompat(
                requireActivity().window,
                requireActivity().window.decorView
            )
            controller.apply {
                isAppearanceLightStatusBars = false
                hide(WindowInsetsCompat.Type.statusBars())
            }
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, isImmerse)

            val params = requireActivity().window.attributes
            params.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            requireActivity().window.attributes = params
        }
    }

    /**
     * 用于初始化Activity的状态栏样式,设置透明状态栏,隐藏状态栏内容。
     */
    @RequiresApi(Build.VERSION_CODES.P)
    fun initActivityBar(context: Activity, isImmerse: Boolean) {
        context.apply {
            window.statusBarColor = Color.TRANSPARENT

            val controller = WindowInsetsControllerCompat(window, window.decorView)
            controller.apply {
                isAppearanceLightStatusBars = false
                hide(WindowInsetsCompat.Type.statusBars())
            }
            WindowCompat.setDecorFitsSystemWindows(window, isImmerse)

            val params = window.attributes
            params.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = params
        }
    }

    /**
     *使用协程流的特性实现了一个简单的定时倒计时功能
     *
     */
    fun countDownCoroutines(
        total: Int,
        scope: CoroutineScope,
        onTick: (Int) -> Unit,
        onStart: (() -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
    ): Job {
        return flow {
            for (i in total downTo 0) {
                emit(i)
                delay(1000)
            }
        }.flowOn(Dispatchers.Main)
            .onStart { onStart?.invoke() }
            .onCompletion { onFinish?.invoke() }
            .onEach { onTick.invoke(it) }
            .launchIn(scope)
    }

    /**
     * 检测网络状态,提示网络异常。
     */
    fun checkNetWork(context: Context) {
        when (NetWorkReceiver.getNetWorkState(context)) {
            -1 -> context.toast("网络状态异常, 请检查网络连接")
        }
    }
}