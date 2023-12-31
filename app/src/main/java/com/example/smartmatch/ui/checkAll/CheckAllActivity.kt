package com.example.smartmatch.ui.checkAll

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
import com.example.smartmatch.databinding.ActivityCheckAllBinding

import com.example.smartmatch.databinding.ActivityFindcBinding
import com.example.smartmatch.ui.checkAll.ui.CheckAllViewModel
import com.example.smartmatch.ui.checkAll.ui.main.CheckAllFragment
import com.example.smartmatch.ui.choiceT.ChoiceTFragment

class CheckAllActivity : BaseActivity<ActivityCheckAllBinding>() ,ChoiceTFragment.OnFragmentInteractionListener{
    var INT_CNUM:String="INT_CNUM"
    var c_all_num=-1;
    var t_nums=0
    val NET_AREA_NAME="name"
    var areaName:String=" "
    private lateinit var checkAllFragment: CheckAllFragment
    private lateinit var activityCheckAllBinding: ActivityCheckAllBinding

    private val checkAllViewModel: CheckAllViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CheckAllViewModel::class.java]
    }
    override fun ActivityCheckAllBinding.initBindingView() {
        binding.findcviewModel = checkAllViewModel
        initActivity()
        initView()
        val intent: Intent =getIntent()
        c_all_num=intent.getIntExtra(INT_CNUM,c_all_num)
        areaName= intent.getStringExtra("areaname")!!
        t_nums=intent.getIntExtra("t_nums",t_nums)
        val bundle= Bundle()
        bundle.putInt("xx",c_all_num)
        bundle.putString("areaname",areaName)
       // bundle.putInt("t_nums",t_nums)
        checkAllFragment.arguments=bundle
    }
    fun initActivity() {
        activityCheckAllBinding= DataBindingUtil.setContentView(this,R.layout.activity_check_all)
        binding.findcviewModel=
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(CheckAllViewModel::class.java)
        activityCheckAllBinding.lifecycleOwner=this

    }

    fun initView() {
        checkAllFragment = CheckAllFragment()
        replaceFragment(checkAllFragment)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.findCFrameLayout, fragment)
        transaction.addToBackStack(null) // 碎片模拟返回栈
        transaction.commitAllowingStateLoss()

    }

    override fun vm() {
        TODO("Not yet implemented")
    }

    override fun vm1() {
        TODO("Not yet implemented")
    }


}