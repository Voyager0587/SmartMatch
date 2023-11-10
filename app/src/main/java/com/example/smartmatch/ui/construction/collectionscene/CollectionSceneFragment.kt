package com.example.smartmatch.ui.construction.collectionscene

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.FragmentCollectionSceneBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.SceneAdapter

/**
 * @className CollectionSceneFragment
 * @description 集群场景
 * @author Voyager
 * @date 2023/10/2 19:06
 */
class CollectionSceneFragment : BaseFragment<FragmentCollectionSceneBinding>(),ConstructionListener {

    private lateinit var adapter: SceneAdapter

//  * private var MutableList:MutableList<ScenariosData>?=null  这边显示的的事area

    private val mViewModel: CollectionSceneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionSceneViewModel::class.java]
    }

    override fun FragmentCollectionSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener=this@CollectionSceneFragment
        mViewModel.getMMNetData()
        initListener()

    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        super.processMMNetData(result)
        result.observe(this) { re ->
            val response = re.getOrNull()
            if (response != null) {
                val data = response.data
                val mmnetDataList = data.mmnet_data
                viewLifecycleOwner.safeLaunch {

                }
                //要选哪个场景要看后面的Fragment的选择的场景
            }

        }
    }

    override fun processFindT(result: LiveData<Result<FindT>>) {
        TODO("Not yet implemented")
    }

    override fun initListener() {
        super.initListener()
        binding.imageBtnAddScene.setOnClickListener {
            // ☆ 跳转到下一个界面

        }
    }

//    fun getCollectionData(mmnet_data: List<MmnetData>):List<>{
//
//    }

    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        super.initRecyclerList(mmnet_data)

    }


}