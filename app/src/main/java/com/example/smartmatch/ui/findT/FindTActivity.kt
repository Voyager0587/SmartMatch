package com.example.smartmatch.ui.findT

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.ActivityFindtBinding
//import com.example.smartmatch.ui.checkCT.CheckCTActivity
import com.example.smartmatch.ui.findT.step_one.FindTStepOneFragment


class FindTActivity : BaseActivity<ActivityFindtBinding>() {

    private lateinit var findTStepOneFragment: FindTStepOneFragment
    private lateinit var activityFindTBinding: ActivityFindtBinding
    val AREA_ID="area_id"
    var area_id:Int=-1
    private val mfindTViewModel: FindTViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FindTViewModel::class.java]
    }
    override fun ActivityFindtBinding.initBindingView() {
        binding.findtviewModel = mfindTViewModel
        initActivity()
        initView()
        val intent: Intent =getIntent()
        val bundle= Bundle()
        area_id=intent.getIntExtra(AREA_ID,area_id)
        bundle.putInt("areaid",area_id)
        Log.e("t_nums_c",area_id.toString())
        findTStepOneFragment.arguments=bundle

       // Connector.getDatabase()
    }
    fun initActivity() {
        //init(R.layout.activity_findc, FindCViewModel::class.java, this)
        activityFindTBinding= DataBindingUtil.setContentView(this,R.layout.activity_findt)
        activityFindTBinding.lifecycleOwner=this

    }

    fun initView() {
        findTStepOneFragment = FindTStepOneFragment()
        replaceFragment(findTStepOneFragment)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.findTFrameLayout, fragment)
        transaction.addToBackStack(null) // 碎片模拟返回栈
        transaction.commitAllowingStateLoss()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        findTStepOneFragment.initViewParams()
    }


}
