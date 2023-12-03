package com.example.smartmatch.ui.choiceT

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivityHfTBinding
import com.example.smartmatch.ui.choiceT.ui.HandFindTFragment
import com.example.smartmatch.ui.choiceT.ui.HandFindTModel

class HandFindTActivity: BaseActivity<ActivityHfTBinding>() {

    private lateinit var handFindTFragment: HandFindTFragment
    private lateinit var activityHandFindTBinding: ActivityHfTBinding
    var INT_TNUM:String="INTTNUM"
    var tnum:Int=-1
    private val handfindTViewModel:HandFindTModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HandFindTModel::class.java]
    }




    fun initActivity() {
        activityHandFindTBinding= DataBindingUtil.setContentView(this,R.layout.activity_hf_t)
        binding.handFindTMoadel=
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HandFindTModel::class.java)
        activityHandFindTBinding.lifecycleOwner=this

    }

    fun initView() {
        handFindTFragment = HandFindTFragment()
        replaceFragment(handFindTFragment)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.handfindFrameLayout, fragment)
        transaction.addToBackStack(null) // 碎片模拟返回栈
        transaction.commitAllowingStateLoss()

    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        handFindTFragment.initViewParams()
    }

    override fun ActivityHfTBinding.initBindingView() {
        binding.handFindTMoadel=handFindTMoadel
        initActivity()
        initView()
        val intent: Intent =getIntent()
        tnum=intent.getIntExtra(INT_TNUM,tnum)
        val bundle= Bundle()
        bundle.putInt("t_num",tnum)
        handFindTFragment.arguments=bundle
    }


}