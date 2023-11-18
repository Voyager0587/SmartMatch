package com.example.smartmatch.ui.construction.clusterscene

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentChooseTargetBinding
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className ChooseTargetFragment
 * @description 集群场景
 * @author Voyager
 * @date 2023/11/17 14:56
 */

class ChooseTargetFragment : BaseFragment<FragmentChooseTargetBinding>(),ConstructionListener{

    private val mViewModel:CollectionSceneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionSceneViewModel::class.java]
    }

    override fun FragmentChooseTargetBinding.initBindingView() {
        binding.viewModel=mViewModel

    }



    override fun processFindT(result: LiveData<Result<FindT>>) {
        TODO("Not yet implemented")
    }


}