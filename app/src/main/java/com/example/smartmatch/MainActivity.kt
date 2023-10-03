package com.example.smartmatch

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.base.util.StatusUtil
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.ActivityMainBinding
import com.example.smartmatch.ui.MainViewModel
import com.example.smartmatch.ui.construction.ConstructionFragment
import com.example.smartmatch.ui.construction.areadefine.AreaDefineFragment
import com.example.smartmatch.ui.construction.scenedefine.SceneDefineFragment
import com.example.smartmatch.ui.feature.FeatureFragment
import com.example.smartmatch.ui.person.PersonFragment

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
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun ActivityMainBinding.initBindingView() {
        binding.viewModel = mViewModel
        initFragment()
        setCurrentFragment(constructionFragment)
        StatusUtil.initActivityBar(this@MainActivity, false)
        safeLaunch {
            mViewModel.jumpToFragment.collect {
                when (it) {
                    1 -> jumpToFragment(AreaDefineFragment(), "areaDefineFragment")
                    2 -> jumpToFragment(SceneDefineFragment(), "sceneDefineFragment")
                    else -> toast("jumpToError")
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
        }

    private fun jumpToFragment(fragment: Fragment, name: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_main, fragment)
            setReorderingAllowed(true)
            addToBackStack(name)
            commit()
        }
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}