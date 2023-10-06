package com.example.smartmatch.ui.construction.collectionscene

import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentCascadeControlBinding
import com.example.smartmatch.ui.construction.adapter.SceneAdapter

/**
 * @className CollectionSceneFragment
 * @description 集合场景
 * @author Voyager
 * @date 2023/10/2 19:06
 */
class CollectionSceneFragment : BaseFragment<FragmentCascadeControlBinding>() {

    private lateinit var adapter: SceneAdapter
//  ! private var MutableList:MutableList<ScenariosData>?=null TODO 这里是不是bean类还没有确定

    private val mViewModel: CollectionSceneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionSceneViewModel::class.java]
    }

    override fun FragmentCascadeControlBinding.initBindingView() {
        binding.viewModel = mViewModel

    }

}