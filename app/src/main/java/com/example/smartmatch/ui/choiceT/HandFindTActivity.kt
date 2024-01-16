package com.example.smartmatch.ui.choiceT



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.base.kxt.getViewBinding
import com.example.smartmatch.databinding.ActivityHfTBinding
import com.example.smartmatch.ui.checkAll.ui.main.CheckAllFragment
import com.example.smartmatch.ui.choiceT.ChoiceTFragment.OnFragmentInteractionListener
import com.example.smartmatch.ui.choiceT.ui.HandFindTFragment
import com.example.smartmatch.ui.choiceT.ui.HandFindTModel


class HandFindTActivity : BaseActivity<ActivityHfTBinding>(), OnFragmentInteractionListener {
    private lateinit var handFindTFragment: HandFindTFragment
    private lateinit var choiceTFragment: ChoiceTFragment
  //  private lateinit var checkAllFragment: CheckAllFragment
    private lateinit var activityHandFindTBinding: ActivityHfTBinding
    var INT_TNUM: String = "INTTNUM"
    var tnum: Int = -1
    private val FindCStepOneViewModel: HandFindTModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FindCStepOneViewModel::class.java]
    }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 确保在这里调用 setContentView
        activityHandFindTBinding = DataBindingUtil.setContentView(this, R.layout.activity_hf_t)
        initActivity()
        initView()
    }

    private fun initActivity() {
        activityHandFindTBinding.lifecycleOwner = this
    }

    fun initView() {
        choiceTFragment = ChoiceTFragment()
        handFindTFragment = HandFindTFragment()
      //  checkAllFragment = CheckAllFragment()
        replaceFragment(handFindTFragment)
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.handfindFrameLayout, fragment)
        transaction.addToBackStack(null) // 碎片模拟返回栈
        transaction.commitAllowingStateLoss()
    }

    override fun ActivityHfTBinding.initBindingView() {

            binding.handFindTMoadel = handFindTMoadel
            initView()
            val intent: Intent = getIntent()
            tnum = intent.getIntExtra(INT_TNUM, tnum)
            val bundle = Bundle()
            bundle.putInt("t_num", tnum)
            handFindTFragment.arguments = bundle

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        handFindTFragment.initViewParams()
    }

    override fun vm() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.handfindFrameLayout, handFindTFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun vm1() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.handfindFrameLayout, choiceTFragment)
            .addToBackStack(null)
            .commit()
    }

//    override fun vm2() {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.handfindFrameLayout, checkAllFragment)
//            .addToBackStack(null)
//            .commit()
//    }
}
