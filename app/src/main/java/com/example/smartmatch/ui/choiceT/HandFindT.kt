package com.example.smartmatch.ui.choiceT

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivityChoiceTBinding
import com.example.smartmatch.databinding.ActivityHandfindtBinding
import com.example.smartmatch.ui.viewModel1.MainViewModel

class HandFindT : BaseActivity<ActivityHandfindtBinding>() {
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }
    override fun ActivityHandfindtBinding.initBindingView() {
        binding.handfindtModel=mViewModel
    }
}