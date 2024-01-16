package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentChooseTBinding

/**
 * @className ChooseTFragment
 * @description 场景详情下选择T的界面
 * @author Voyager
 * @date 2024/1/16 11:15
 */

class ChooseTFragment : BaseFragment<FragmentChooseTBinding>() {

    private val mViewModel: SceneDetailsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SceneDetailsViewModel::class.java]
    }

    override fun FragmentChooseTBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.getLights(mViewModel.scenarioId.toString())
        btnFlash.setOnClickListener {
//            mViewModel.flicker()
        }
        submit.setOnClickListener {

        }

    }


}