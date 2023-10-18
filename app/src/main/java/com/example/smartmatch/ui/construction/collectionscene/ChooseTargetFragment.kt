package com.example.smartmatch.ui.construction.collectionscene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentChooseTargetBinding
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.ui.construction.ConstructionListener


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

    override fun initFragment(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TODO("Not yet implemented")
    }

    override fun processFindT(result: LiveData<Result<FindT>>) {
        TODO("Not yet implemented")
    }


}