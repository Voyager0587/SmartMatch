package com.example.smartmatch.ui.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun initFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TODO("Not yet implemented")
    }

}