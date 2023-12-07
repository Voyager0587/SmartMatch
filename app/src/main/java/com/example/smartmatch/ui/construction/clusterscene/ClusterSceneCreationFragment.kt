package com.example.smartmatch.ui.construction.clusterscene

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentCollectionSceneCreationBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.helper.CollectionScenarioHelper
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.ClusterScenarioAdapter

/**
 * @className CollectionSceneCreationFragment
 * @description 集群场景创建
 * @author Voyager
 * @date 2023/10/10 14:53
 */
class ClusterSceneCreationFragment : BaseFragment<FragmentCollectionSceneCreationBinding>(),
    ConstructionListener {

    lateinit var adapter: ClusterScenarioAdapter
    lateinit var sceneList: MutableList<CollectionScenarioHelper>
    private val mViewModel: CollectionSceneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionSceneViewModel::class.java]
    }

    override fun FragmentCollectionSceneCreationBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@ClusterSceneCreationFragment
        initListener()

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

    override fun initListener() {
        super.initListener()
        binding.imageBtnAddBasisScene.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().hide(this)
                .add(ChooseTargetFragment(), "chooseTargetFragment")
                .addToBackStack("clusterSceneCreationFragment").commit()
        }
    }
}