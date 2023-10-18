package com.example.smartmatch.ui.checkCT

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivityCheckctBinding
import com.example.smartmatch.ui.MainViewModel

class CheckCTActivity : BaseActivity<ActivityCheckctBinding>(){
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    override fun ActivityCheckctBinding.initBindingView() {
        binding.checkctModel=mViewModel

    }
}