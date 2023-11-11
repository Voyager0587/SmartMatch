package com.example.smartmatch.ui.construction.collectionscene

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentCollectionSceneCreationBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.helper.CollectionScenarioHelper
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.CollectionScenarioAdapter

/**
 * @className CollectionSceneCreationFragment
 * @description 集群场景创建
 * @author Voyager
 * @date 2023/10/10 14:53
 */
class CollectionSceneCreationFragment : BaseFragment<FragmentCollectionSceneCreationBinding>(),
    ConstructionListener {

    lateinit var adapter: CollectionScenarioAdapter
    lateinit var sceneList: MutableList<CollectionScenarioHelper>
    private val mViewModel: CollectionSceneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionSceneViewModel::class.java]
    }

    override fun FragmentCollectionSceneCreationBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@CollectionSceneCreationFragment


    }


    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        super.processMMNetData(result)
        result.observe(this) { re ->
            val response = re.getOrNull()
            if (response != null) {
                //这边是创建集合场景

            }

        }
    }
}