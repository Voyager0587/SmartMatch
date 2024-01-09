package com.example.smartmatch.ui.findC


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.ActivityFindcBinding
import com.example.smartmatch.databinding.ActivityMainBinding
import com.example.smartmatch.ui.checkAll.ui.main.CheckAllFragment
import com.example.smartmatch.ui.choiceT.ChoiceTFragment
import com.example.smartmatch.ui.choiceT.ui.HandFindTFragment
import com.example.smartmatch.ui.findC.step_one.FindCStepOneFragment
import com.example.smartmatch.ui.findC.step_one.FindCStepOneViewModel


class FindCActivity : BaseActivity<ActivityFindcBinding>(),ChoiceTFragment.OnFragmentInteractionListener{
    private lateinit var handFindTFragment: HandFindTFragment
    private lateinit var choiceTFragment: ChoiceTFragment
    private lateinit var checkAllFragment: CheckAllFragment
    private lateinit var findCStepOneFragment: FindCStepOneFragment
    private lateinit var activityFindcBinding: ActivityFindcBinding
    val NET_ID="net_id"
    var user_id:Int=-1
    var area_name:String=""
    private val mfindcViewModel:  FindCStepOneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FindCStepOneViewModel::class.java]
    }
    override fun ActivityFindcBinding.initBindingView() {
        binding.findcviewModel = mfindcViewModel
        initActivity()
        initView()
        val intent:Intent=getIntent()
        val bundle=Bundle()
        user_id=intent.getIntExtra(NET_ID,user_id)
        area_name= intent.getStringExtra("areaname").toString()
        bundle.putInt("xx",user_id)
        bundle.putString("areaname",area_name)
        findcviewModel!!.name_area=area_name
        mfindcViewModel.name_area.toast()
        Log.e("t_nums_c",area_name.toString())
        findCStepOneFragment.arguments=bundle
    }
    fun initActivity() {
        activityFindcBinding=DataBindingUtil.setContentView(this,R.layout.activity_findc)
        activityFindcBinding.lifecycleOwner=this

    }

    fun initView() {
        handFindTFragment= HandFindTFragment()
        checkAllFragment= CheckAllFragment()
        choiceTFragment= ChoiceTFragment()
        findCStepOneFragment = FindCStepOneFragment()
        replaceFragment(findCStepOneFragment)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.findCFrameLayout, fragment)
        transaction.addToBackStack(null) // 碎片模拟返回栈
        transaction.commitAllowingStateLoss()

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        findCStepOneFragment.initViewParams()
    }

    override fun vm() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.findCFrameLayout, handFindTFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun vm1() {
        TODO("Not yet implemented")
    }

}