package com.example.smartmatch.ui.construction.lightcontrol

import android.annotation.SuppressLint
import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.FragmentLightControlSceneBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.ScenariosData
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.logic.network.model.ResponseMessage
import com.example.smartmatch.logic.network.model.Scenario
import com.example.smartmatch.logic.network.model.ScenarioResponse
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.LightControlScenarioAdapter

/**
 * @className LightControlScenarioFragment
 * @description
 * @author Voyager 照明控制界面的场景控制
 * @date 2023/10/6 17:10
 */
class LightControlScenarioFragment(
    private val mmnet_index: Int,
    private val area_index: Int,
    private val fragment: LightControlFragment
) : BaseFragment<FragmentLightControlSceneBinding>(), ConstructionListener {
    private var list: List<ScenariosData> = mutableListOf()
    private var brightness_adjustment_percentage = 0
    private lateinit var adapter: LightControlScenarioAdapter
    private val mViewModel: LightControlViewModel by lazy {
        ViewModelProvider(
            fragment,
            ViewModelProvider.NewInstanceFactory()
        )[LightControlViewModel::class.java]
    }

    override fun FragmentLightControlSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener=this@LightControlScenarioFragment
        initRecyclerList()
        initListener()
    }

    override fun initListener() {
        super.initListener()
        binding.swSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                mViewModel.instructScenario(getInstructionScenario())
            }
            else
                mViewModel.closeScene()

        }

        binding.seekBarAdjustBrightness.max=400
        binding.seekBarAdjustBrightness.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // SeekBar的进度发生改变时执行的操作
                binding.displayPercentage.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // 用户开始拖动SeekBar时执行的操作

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // 用户停止拖动SeekBar时执行的操作
                brightness_adjustment_percentage = seekBar.progress
                adapter.adjustPercentage=seekBar.progress.toFloat()
            }

        })

    }

    override fun processResponse(result: LiveData<Result<ResponseMessage>>) {
        super.processResponse(result)
        result.observe(this) {
            if(it.isSuccess){
                val responseMessage = it.getOrNull()
                if(responseMessage?.code==1){
                    adapter.notifyDataSetChanged()
                    toast("控制成功")
                }
                else toast(responseMessage?.msg)
            }
        }
    }

    override fun processFindT(result: LiveData<Result<FindT>>) {

    }


    private fun initRecyclerList() {

        viewLifecycleOwner.safeLaunch {
            val mmnetData = mViewModel.mmnetData?.value?.getOrNull()
            list = mmnetData?.let { searchData(it) }!!
            adapter = LightControlScenarioAdapter(this@LightControlScenarioFragment)
            binding.recyclerScenario.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerScenario.adapter = adapter
            adapter.setData(list)
        }

    }

    private fun getInstructionScenario(): ScenarioResponse {
        val scenariosDataList = adapter.scenarioList
        val scenarioList = ArrayList<Scenario>()
        for (scene in scenariosDataList) {
            val scenario =
                Scenario(scene.id,
                    (scene.required_percentage *4* brightness_adjustment_percentage).toInt()
                )
            scenarioList.add(scenario)
        }
        return ScenarioResponse(scenarioList,if(binding.swSwitch.isChecked)1 else 0)
    }

    private fun searchData(mmnetData: MMNetResponse): List<ScenariosData> {
        return mmnetData.data.mmnet_data.getOrNull(mmnet_index)?.areas?.areas_data?.getOrNull(
            area_index
        )?.area?.scenarios_data ?: emptyList()
    }




}
