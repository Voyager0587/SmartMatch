package com.example.smartmatch.ui.construction.collectioncontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentChooseScenarioBinding
import com.example.smartmatch.logic.network.model.CollectionScenariosResponse
import com.example.smartmatch.ui.construction.ConstructionListener


class ChooseScenarioFragment : BaseFragment<FragmentChooseScenarioBinding>(), ConstructionListener {

    private val mViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionControlViewModel::class.java]
    }
    override fun FragmentChooseScenarioBinding.initBindingView() {
        binding.viewModel=mViewModel
        mViewModel.constructionListener=this@ChooseScenarioFragment


    }

    override fun initListener() {
        super.initListener()
    }

    override fun processCollectionScenariosResponse(result: LiveData<Result<CollectionScenariosResponse>>) {
        super.processCollectionScenariosResponse(result)
    }




}