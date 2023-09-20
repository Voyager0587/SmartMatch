package com.example.smartmatch

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.ActivityMainBinding
import com.example.smartmatch.ui.MainViewModel
import com.example.smartmatch.ui.construction.ConstructionFragment
import com.example.smartmatch.ui.construction.areadefine.AreaDefineFragment
import com.example.smartmatch.ui.feature.FeatureFragment
import com.example.smartmatch.ui.person.PersonFragment
import kotlinx.coroutines.launch

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

    override fun ActivityMainBinding.initBindingView() {
        binding.viewModel = mViewModel
        initFragment()
        setCurrentFragment(constructionFragment)

        with(mViewModel) {
            lifecycleScope.launch{
                mViewModel._jumpToArea.collect{
                    when(it)
                    {
                        1-> jumpToFragment(AreaDefineFragment(),"areaDefineFragment")
                        else -> toast("jumpToError")
                    }
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