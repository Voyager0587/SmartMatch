package com.example.smartmatch

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.base.util.StatusUtil
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.base.kxt.initSp
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.ActivityMainBinding
import com.example.smartmatch.ui.MainViewModel
import com.example.smartmatch.ui.construction.ConstructionFragment
import com.example.smartmatch.ui.construction.areadefine.AreaDefineFragment
import com.example.smartmatch.ui.construction.lightcontrol.LightControlFragment
import com.example.smartmatch.ui.construction.scenedefine.SceneDefineFragment
import com.example.smartmatch.ui.feature.FeatureFragment
import com.example.smartmatch.ui.person.PersonFragment
import com.example.smartmatch.ui.person.login.LoginFragment

/**
 * @className MainActivity
 * @description 主Activity
 * @author Voyager
 * @date 2023/9/30 20:21
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var featureFragment: FeatureFragment
    private lateinit var constructionFragment: ConstructionFragment
    private lateinit var personFragment: PersonFragment
    private lateinit var mCurrentFragment:Fragment
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun ActivityMainBinding.initBindingView() {
        binding.viewModel = mViewModel
        SmartApplication.sp=initSp()
        initFragment()
        setCurrentFragment(constructionFragment)
        StatusUtil.initActivityBar(this@MainActivity, false)
        safeLaunch {
            mViewModel.jumpToFragment.collect {
                when (it) {
                    1 -> jumpToFragment(AreaDefineFragment(), "areaDefineFragment")
                    2 -> jumpToFragment(SceneDefineFragment(), "sceneDefineFragment")
                    3 -> jumpToFragment(LightControlFragment(), "lightControlFragment")
                    4 -> jumpToFragment(LoginFragment(), "loginFragment")
                    else -> toast("jumpToError")
                }
            }
        }
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.person -> {
                    setCurrentFragment(personFragment)
                    true
                }
                R.id.construction -> {
                    setCurrentFragment(constructionFragment)
                    true
                }
                R.id.feature_entry -> {
                    setCurrentFragment(featureFragment)
                    true
                }
                else -> {
                    toast("你点啥了？？")
                    false
                }
            }
        }

    }

    private fun initFragment() {
        featureFragment = FeatureFragment()
        constructionFragment = ConstructionFragment()
        personFragment = PersonFragment()
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
            commit()
            mCurrentFragment=fragment
        }

    private fun jumpToFragment(fragment: Fragment, name: String) {
        //通过back回退，之前处于hide状态的Fragment会再次显现
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container_main, fragment)
            hide(mCurrentFragment)    //TODO 改成mCurrentFragment
            setReorderingAllowed(true)
            addToBackStack(name)
            commit()

            // * fragment切换用add,hide,show方法进行，再加一个“上一步”的按钮
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}