package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentLightDetailBinding
import com.example.smartmatch.logic.model.helper.ItemHelper
import com.example.smartmatch.logic.model.helper.LightBean
import com.example.smartmatch.logic.model.helper.LightDetail
import com.example.smartmatch.ui.construction.adapter.LightDetailsAdapter

/**
 * @className LightDetailFragment
 * @description 场景详情下的灯的详情界面
 * @author Voyager
 * @date 2024/1/12 22:49
 */

class LightDetailFragment : BaseFragment<FragmentLightDetailBinding>() {
    private lateinit var adapter: LightDetailsAdapter
    private val lightList=ArrayList<ItemHelper>()
    private var adjustPercentage:Float=-100.0F
    private val mViewModel: SceneDetailsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SceneDetailsViewModel::class.java]
    }

    override fun FragmentLightDetailBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.getScenarioDetail(mViewModel.scenarioId)
        initRecyclerView()
        imageBtnAdd.setOnClickListener {
            mViewModel.switchFragment(ChooseTFragment(),"ChooseTFragment")
        }
        btnTest.setOnClickListener {
            val lightList=ArrayList<LightDetail>()
            for (i in 0 until adapter.existList.size) {
                lightList.add(LightDetail(adapter.existList[i].id, adapter.existList[i].percentage))
            }
            mViewModel.submitLight(mViewModel.scenarioId.toString(),LightBean(lightList))
        }
        back.setOnClickListener {
            mViewModel.switchFragment(ChooseTFragment(),"ChooseTFragment")
        }
        allOff.setOnClickListener {
            mViewModel.letAllLightsOff(mViewModel.scenarioId.toString())
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                TODO("Not yet implemented")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        delete.setOnClickListener {
            val lightList=ArrayList<LightDetail>()
            for (i in 0 until adapter.existList.size) {
                lightList.add(LightDetail(adapter.existList[i].id, adapter.existList[i].percentage))
            }
            mViewModel.deleteLight(mViewModel.scenarioId.toString(), LightBean(lightList))
        }

    }

    override fun onResume() {
        super.onResume()
        if(mViewModel.scenarioAddedList.size != 0) {
            lightList.addAll(mViewModel.scenarioAddedList)
            mViewModel.scenarioAddedList=ArrayList<ItemHelper>()
        }
    }

    private fun initRecyclerView(){
        val details= mViewModel.sceneDetailsData.value?.getOrNull()?.data
        val lightDetail=details?.light_data
        for (i in 0 until lightDetail?.size!!) {
            lightList.add(ItemHelper("灯$i", lightDetail[i].id, lightDetail[i].definitional_percentage))
        }
        adapter = LightDetailsAdapter()
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.adapter=adapter
        adapter.setData(lightList)
    }
}