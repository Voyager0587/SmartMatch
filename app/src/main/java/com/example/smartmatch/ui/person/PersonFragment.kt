package com.example.smartmatch.ui.person

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentPersonBinding
import com.example.smartmatch.ui.MainViewModel

class PersonFragment : BaseFragment<FragmentPersonBinding>() {

    // ! 必须要用这个requireActivity()，否则不会collect到数据
    private val mViewModel :MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    override fun FragmentPersonBinding.initBindingView() {
        binding.viewModel=mViewModel
        llLogin.setOnClickListener {
            mViewModel.jumpToLogin(4)
        }
    }

}