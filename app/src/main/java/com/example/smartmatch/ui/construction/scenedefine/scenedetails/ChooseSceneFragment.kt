package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentChooseSceneBinding
import com.example.smartmatch.logic.model.helper.ItemHelper
import com.example.smartmatch.logic.model.helper.Scenario
import com.example.smartmatch.logic.model.helper.ScenarioLight
import com.example.smartmatch.logic.network.model.CanAddScenesResponse
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.SceneDetailsAdapter

/**
 * @className ChooseSceneFragment
 * @description 场景详情下的选择场景界面
 * @author Voyager
 * @date 2024/1/12 22:30
 */

class ChooseSceneFragment : BaseFragment<FragmentChooseSceneBinding>(), ConstructionListener {
    private lateinit var adapter: SceneDetailsAdapter
    private val mViewModel: SceneDetailsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SceneDetailsViewModel::class.java]
    }

    override fun FragmentChooseSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.getAddScenario(mViewModel.scenarioId.toString())
        relativeLayout.setOnClickListener {
            if (binding.recyclerView.visibility == View.VISIBLE)
                binding.recyclerView.visibility = View.GONE
            else
                binding.recyclerView.visibility = View.VISIBLE

        }
        submit.setOnClickListener {
            var scene=adapter.existList
            val scenarioList=ArrayList<Scenario>()
            for (i in 0 until scene.size) {
                scenarioList.add(Scenario(scene[i].id, scene[i].percentage))
            }
            mViewModel.scenarioAddedList=scene
            mViewModel.addScenario(mViewModel.scenarioId.toString(),ScenarioLight(scenarioList))
            mViewModel.switchFragment(SubSceneFragment(),"SubSceneFragment")
        }

    }

    override fun processAddSceneResponse(result: LiveData<Result<CanAddScenesResponse>>) {
        super.processAddSceneResponse(result)
        result.observe(this) { re ->
            re?.getOrNull()?.let { response ->
                val sceneDataList = response.data
                val sceneList=ArrayList<ItemHelper>()
                for (i in 0 until sceneDataList?.size!!) {
                    sceneList.add(ItemHelper("灯$i", sceneDataList[i].scenario_id, -1.0))
                }
                adapter = SceneDetailsAdapter()
                binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
                binding.recyclerView.adapter=adapter
                adapter.setData(sceneList)
            }
        }

    }

}