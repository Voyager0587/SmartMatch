package com.example.base.util

/**
 * @author:SunShibo
 * @date:2023-04-06 23:38
 * @feature:
 */
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author MrLiu
 * @date 2020/5/15
 * 网络链接
 * desc 广播接收者
 */
class NetWorkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        // 特殊注意：如果if条件生效，那么证明当前是有连接wifi或移动网络的，如果有业务逻辑最好把esle场景酌情考虑进去！
        if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
            val time = time
            if (time != WIFI_TIME && time != ETHERNET_TIME && time != NONE_TIME) {
                val netWorkState = getNetWorkState(context)
                if (netWorkState == 0 && LAST_TYPE != 0) {
                    WIFI_TIME = time
                    LAST_TYPE = netWorkState
                    Log.e(TAG, "wifi：$time")
                } else if (netWorkState == 1 && LAST_TYPE != 1) {
                    ETHERNET_TIME = time
                    LAST_TYPE = netWorkState
                    Log.e(TAG, "数据网络：$time")
                } else if (netWorkState == -1 && LAST_TYPE != -1) {
                    NONE_TIME = time
                    LAST_TYPE = netWorkState
                    Log.e(TAG, "无网络：$time")
                }
            }
        }
    }

    val time: Long
        get() {
            val sDateFormat = SimpleDateFormat("yyyyMMddhhmmss")
            val date = sDateFormat.format(Date())
            return java.lang.Long.valueOf(date)
        }

    companion object {
        private var WIFI_TIME: Long = 0
        private var ETHERNET_TIME: Long = 0
        private var NONE_TIME: Long = 0
        private var LAST_TYPE = -3
        private const val TAG = "TAG"
        private const val NETWORK_NONE = -1 //无网络连接
        private const val NETWORK_WIFI = 0 //wifi
        private const val NETWORK_MOBILE = 1 //数据网络

        //判断网络状态与类型
        fun getNetWorkState(context: Context): Int {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    return NETWORK_WIFI
                } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    return NETWORK_MOBILE
                }
            } else {
                return NETWORK_NONE
            }
            return NETWORK_NONE
        }
    }
}