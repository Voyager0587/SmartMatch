package com.example.smartmatch.ui.construction.collectioncontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentNewCollectionSceneBinding
import com.example.smartmatch.logic.model.helper.ScenarioHelper
import com.example.smartmatch.logic.network.model.CollectionScenariosResponse
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.CollectionScenarioAdapter

/**
 * @className NewCollectionSceneFragment
 * @description 新建集合场景
 * @author Voyager
 * @date 2023/11/15 22:02
 */

class NewCollectionSceneFragment(private val mmnetId: Int) :
    BaseFragment<FragmentNewCollectionSceneBinding>(),
    ConstructionListener {
    private val mViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionControlViewModel::class.java]
    }
    private val dataList = ArrayList<ScenarioHelper>()
    private lateinit var mAdapter: CollectionScenarioAdapter
    override fun FragmentNewCollectionSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@NewCollectionSceneFragment
        mViewModel.netId = mmnetId
        initListener()
        mViewModel.getCollectionScenarioData()
    }


    override fun processCollectionScenariosResponse(result: LiveData<Result<CollectionScenariosResponse>>) {
        super.processCollectionScenariosResponse(result)
        result.observe(this) { re ->
            run {
                val data = re.getOrNull()
                data?.let {
                    for (bean in data.data)
                        dataList.add(ScenarioHelper(bean.name, bean.id))
                    mAdapter = CollectionScenarioAdapter()
                    binding.newCollectionListRecyclerView.apply {
                        adapter = mAdapter
                        layoutManager = LinearLayoutManager(requireContext())
                        mAdapter.setData(dataList)
                    }

                }
            }
        }
    }

    override fun initListener() {
        super.initListener()

        binding.imageBtnAddCollectionScene.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(
                    R.id.container_main,
                    AddCollectionSceneFragment(this@NewCollectionSceneFragment,mmnetId)
                )
                hide(this@NewCollectionSceneFragment)    //TODO 改成mCurrentFragment
                setReorderingAllowed(true)
                addToBackStack("new")
                commit()
            }
        }
        binding.imageBtnAddClusterScene.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                add(
                    R.id.container_main,
                    ChooseScenarioFragment(this@NewCollectionSceneFragment)
                )
                hide(this@NewCollectionSceneFragment)    //TODO 改成mCurrentFragment
                setReorderingAllowed(true)
                addToBackStack("new")
                commit()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(!dataList.contains(mViewModel.scenarioHelper))
            mViewModel.scenarioHelper?.let {
                dataList.add(it)
                mViewModel.scenarioHelper=null
            }

    }
}