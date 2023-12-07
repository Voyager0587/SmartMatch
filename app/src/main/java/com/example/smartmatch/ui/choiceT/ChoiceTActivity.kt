package com.example.smartmatch.ui.choiceT

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.chart.checkTAll
import com.example.smartmatch.databinding.ActivityChoiceTBinding
import com.example.smartmatch.ui.MainViewModel
import com.example.smartmatch.ui.choiceT.ui.ChoiceTRvAdapter
import com.example.smartmatch.ui.choiceT.ui.HandFindTModel

class ChoiceTActivity :BaseActivity<ActivityChoiceTBinding>(){
    var i:Int=-1
    private lateinit var choiceTFragment: ChoiceTFragment
    private lateinit var activityChoiceTBinding: ActivityChoiceTBinding
    private val mViewModel: HandFindTModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[HandFindTModel::class.java]
    }

    override fun ActivityChoiceTBinding.initBindingView() {

        binding.choiceFindTMoadel=mViewModel

        initActivity()
        initView()
        val intent: Intent =getIntent()
        }
        fun initActivity() {
        activityChoiceTBinding= DataBindingUtil.setContentView(this, R.layout.activity_choice_t)
        binding.choiceFindTMoadel=
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HandFindTModel::class.java)
        activityChoiceTBinding.lifecycleOwner=this

    }

    fun initView() {
        choiceTFragment= ChoiceTFragment()
        replaceFragment(choiceTFragment)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.choiceTFrameLayout, fragment)
        transaction.addToBackStack(null) // 碎片模拟返回栈
        transaction.commitAllowingStateLoss()

    }






    }















//    fun data(): List<checkTAll> {
//        for (i in 1..5) {
//            // list.add(checkTAll(i))
//            Log.e("iddddddd",list.size.toString())
//        }
//        return list
//    }
