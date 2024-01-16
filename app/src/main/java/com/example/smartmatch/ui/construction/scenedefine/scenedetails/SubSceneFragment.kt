package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentSubSceneBinding
import com.example.smartmatch.logic.model.helper.ItemHelper
import com.example.smartmatch.logic.model.helper.Scenario
import com.example.smartmatch.logic.model.helper.ScenarioLight
import com.example.smartmatch.ui.construction.adapter.LightDetailsAdapter

class SubSceneFragment : BaseFragment<FragmentSubSceneBinding>() {
    private lateinit var adapter: LightDetailsAdapter
    private var percentage:Double=100.0
    private  val scenarioList=ArrayList<ItemHelper>()
    private val mViewModel: SceneDetailsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SceneDetailsViewModel::class.java]
    }
    override fun FragmentSubSceneBinding.initBindingView() {
        binding.viewModel=mViewModel
        initRecyclerView()
        mViewModel.getSubScenarioDetails(mViewModel.scenarioId.toString())
        imageBtnAdd.setOnClickListener {
            mViewModel.switchFragment(ChooseSceneFragment(),"ChooseSceneFragment")
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                percentage=progress.toDouble()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //如果所有scene中有选中的scene的id，就修改percentage
                for (i in 0 until adapter.existList.size)
                    if(scenarioList.find { it.id==adapter.existList[i].id }!=null){
                        adapter.existList[i].percentage=percentage
                        scenarioList.find { it.id==adapter.existList[i].id  }!!.percentage=percentage
                    }


            }
        })
        btnTest.setOnClickListener {
            val sceneList=ArrayList<Scenario>()
            for (i in 0 until adapter.existList.size) {
                sceneList.add(Scenario(adapter.existList[i].id, adapter.existList[i].percentage))
            }
            mViewModel.switchScenario(mViewModel.scenarioId.toString(), ScenarioLight(sceneList))
        }
        back.setOnClickListener {
            mViewModel.switchFragment(ChooseSceneFragment(),"ChooseSceneFragment")
        }
        allOff.setOnClickListener {
            mViewModel.letAllLightsOff(mViewModel.scenarioId.toString())
        }
        delete.setOnClickListener {
            val sceneList=ArrayList<Scenario>()
            for (i in 0 until adapter.existList.size) {
                sceneList.add(Scenario(adapter.existList[i].id, adapter.existList[i].percentage))
            }
            mViewModel.deleteScenario(mViewModel.scenarioId.toString(), ScenarioLight(sceneList))
        }
    }

    override fun onResume() {
        super.onResume()
        if(mViewModel.scenarioAddedList.size != 0) {
            scenarioList.addAll(mViewModel.scenarioAddedList)
            mViewModel.scenarioAddedList=ArrayList<ItemHelper>()
        }

    }

    private fun initRecyclerView(){
        val details= mViewModel.subScenarioDetailsData?.value?.getOrNull()?.data
        for (i in 0 until details?.size!!) {
            ItemHelper(
                details[i].name, details[i].id,
                details[i].definitional_percentage,
            )
                .let { scenarioList.add(it) }
        }
        adapter = LightDetailsAdapter()
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
        adapter.setData(scenarioList)
    }

}