package com.example.smartmatch.ui.construction

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentConstructionBinding
import com.example.smartmatch.ui.MainViewModel

/**
 * @className ConstructionFragment
 * @description 施工界面
 * @author Voyager
 * @date 2023/9/20 13:50
 */

class ConstructionFragment : BaseFragment<FragmentConstructionBinding>() {

    private val mViewModel:MainViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }
    override fun FragmentConstructionBinding.initBindingView() {
        binding.viewModel=mViewModel
        areaDefineLayout.setOnClickListener {
            mViewModel.jumpToAreaDefine(1)
        }

    }

}