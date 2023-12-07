package com.example.smartmatch.ui.construction.collectioncontrol

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentAddCollectionSceneBinding
import com.example.smartmatch.logic.model.helper.ScenarioHelper
import com.example.smartmatch.logic.network.model.CollectionScenariosResponse
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.CollectionScenarioAdapter

/**
 * @className AddCollectionSceneFragment
 * @description 集合场景添加场景
 * @author Voyager
 * @date 2023/11/16 1:00
 */

class AddCollectionSceneFragment(private val fragment: Fragment,private val netId:Int) :
    BaseFragment<FragmentAddCollectionSceneBinding>(),
    ConstructionListener {

    private lateinit var mAdapter: CollectionScenarioAdapter
    private val dataList = ArrayList<ScenarioHelper>()
    private val mViewModel by lazy {
        ViewModelProvider(
            fragment,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionControlViewModel::class.java]
    }


    override fun FragmentAddCollectionSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@AddCollectionSceneFragment
        mViewModel.netId=netId
        mViewModel.getCollectionScenarioData()
        initListener()
    }

    override fun initListener() {
        super.initListener()
        binding.btnConfirm.setOnClickListener {
            if(mAdapter.scenarioHelper==null){
                "Please select a collection scene".toast()
                return@setOnClickListener
            }
            mViewModel.scenarioHelper=mAdapter.scenarioHelper
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    override fun processCollectionScenariosResponse(result: LiveData<Result<CollectionScenariosResponse>>) {
        super.processCollectionScenariosResponse(result)
        result.observe(this) { re ->
            run {
                val response = re.getOrNull()

                response?.let {

                    for (scene in response.data)
                        dataList.add(ScenarioHelper(scene.name, scene.id))
                    initRecyclerView(dataList)
                }
            }
        }
    }

    private fun initRecyclerView(dataList: List<ScenarioHelper>) {
        mAdapter = CollectionScenarioAdapter()
        binding.netRecyclerview.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter.setData(dataList)

        }
    }
}