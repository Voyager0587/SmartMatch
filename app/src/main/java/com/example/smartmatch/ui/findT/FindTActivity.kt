package com.example.smartmatch.ui.findT

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivityFindcBinding
import com.example.smartmatch.databinding.ActivityFindtBinding
import com.example.smartmatch.ui.findT.step_one.FindTStepOneFragment
import com.example.smartmatch.ui.findC.step_one.FindCStepOneFragment
import com.example.smartmatch.ui.viewModel1.FindCViewModel
import com.example.smartmatch.ui.viewModel1.FindTViewModel


class FindTActivity : BaseActivity<ActivityFindtBinding>() {

    private lateinit var findTStepOneFragment: FindTStepOneFragment
    private lateinit var activityFindTBinding: ActivityFindtBinding
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
       // Connector.getDatabase()
    }
    fun initActivity() {
        //init(R.layout.activity_findc, FindCViewModel::class.java, this)
        activityFindTBinding= DataBindingUtil.setContentView(this,R.layout.activity_findt)
        binding.findtviewModel=
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FindTViewModel::class.java)
        activityFindTBinding.lifecycleOwner=this

    }

    fun initView() {
        findTStepOneFragment = FindTStepOneFragment()
        replaceFragment(findTStepOneFragment)
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
