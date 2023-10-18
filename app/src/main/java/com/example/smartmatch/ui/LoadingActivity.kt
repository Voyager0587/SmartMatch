package com.example.smartmatch.ui

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivityLoadingBinding
import com.example.smartmatch.ui.viewModel1.MainViewModel

class LoadingActivity:BaseActivity<ActivityLoadingBinding>() {

private val mViewModel: MainViewModel by lazy {
    ViewModelProvider(
        this,
        ViewModelProvider.NewInstanceFactory()
    )[MainViewModel::class.java]
}

    override fun ActivityLoadingBinding.initBindingView() {
        binding.loadingViewModel=mViewModel
    }

}