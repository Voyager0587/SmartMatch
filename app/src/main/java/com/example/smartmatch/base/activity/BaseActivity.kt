package com.example.smartmatch.base.activity

import android.Manifest
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

import com.example.base.util.NetWorkReceiver
import com.example.base.util.StatusUtil
import com.example.base.util.StatusUtil.checkNetWork
import com.example.smartmatch.base.`interface`.BaseBinding
import com.example.smartmatch.base.kxt.getViewBinding
import com.example.smartmatch.base.kxt.toast


/**

 *
 * BaseActivity没有实现BaseBinding接口中的initBindingView()方法，是因为BaseActivity是一个抽象类，它只是声明了自己实现了BaseBinding接口，并且定义了一个binding属性来获取视图绑定。
 * 但是具体的initBindingView()方法的实现逻辑需要在继承BaseActivity的子类中完成。
 * 这样做的目的是为了让每个继承BaseActivity的子类都可以根据自己的需求来实现initBindingView()方法，以完成自定义的视图绑定逻辑。
*/

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(), BaseBinding<VB> {
    private val requestPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            for ((name, granted) in isGranted) {
                if (!granted) {
                    toast("${name}权限请求失败")
                }
            }

        }




    private lateinit var netWorkReceiver: NetWorkReceiver

    protected val binding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        getViewBinding(layoutInflater)
    }

    /**
     * 继承自BaseActivity的Activity只需要重写onCreate()方法并调用super.onCreate(savedInstanceState)就可以执行下面这些初始化
     */
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置布局文件
        setContentView(binding.root)
        //初始化视图绑定,在继承此Activity中重写
        binding.initBindingView()
        //检查并请求所需权限
        initPermission()
        //设置状态栏透明
        initStatusBsr()
        //初始化网络广播接收器
        initNetWorker()
    }

    private fun initNetWorker() {
        netWorkReceiver = NetWorkReceiver()
        val filter = IntentFilter()
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkReceiver, filter);
        checkNetWork(this)
    }

    override fun onResume() {
        super.onResume()
        checkNetWork(this)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun initStatusBsr() {
        StatusUtil.initActivityBar(this@BaseActivity, true)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun initPermission() {
        requestPermissions.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.FOREGROUND_SERVICE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH_ADVERTISE
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(netWorkReceiver)
    }

}