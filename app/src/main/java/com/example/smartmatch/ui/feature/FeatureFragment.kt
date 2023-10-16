package com.example.smartmatch.ui.feature

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentFeatureBinding
import com.example.smartmatch.ui.viewModel1.MainViewModel


class FeatureFragment : BaseFragment<FragmentFeatureBinding>() {
    private val mViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }
    override fun FragmentFeatureBinding.initBindingView() {
        binding.viewModel=mViewModel

    }


}