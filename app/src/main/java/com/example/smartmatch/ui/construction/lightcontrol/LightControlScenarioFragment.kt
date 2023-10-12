package com.example.smartmatch.ui.construction.lightcontrol

import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.FragmentLightControlSceneBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.ScenariosData
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
) : BaseFragment<FragmentLightControlSceneBinding>(),ConstructionListener {
    private var list: List<ScenariosData> = mutableListOf()
    private lateinit var adapter: LightControlScenarioAdapter
    private val mViewModel: LightControlViewModel by lazy {
        ViewModelProvider(
            fragment,
            ViewModelProvider.NewInstanceFactory()
        )[LightControlViewModel::class.java]
    }

    override fun FragmentLightControlSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        initRecyclerList()

    }

    override fun initListener() {
        super.initListener()
        binding.swPauseActive.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){

            }else{

            }
        }

        binding.seekBarAdjustBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // SeekBar的进度发生改变时执行的操作
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // 用户开始拖动SeekBar时执行的操作
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // 用户停止拖动SeekBar时执行的操作
            }

        })

        binding.swSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){

            }else{

            }
        }
    }

    private fun initRecyclerList() {

         viewLifecycleOwner.safeLaunch {
             val mmnetData = mViewModel.mmnetData?.value?.getOrNull()
             list= mmnetData?.let { searchData(it) }!!
             adapter = LightControlScenarioAdapter(this@LightControlScenarioFragment)
             binding.recyclerScenario.layoutManager = LinearLayoutManager(requireContext())
             binding.recyclerScenario.adapter = adapter
             adapter.setData(list)
         }

    }

    private suspend fun searchData(mmnetData: MMNetResponse): List<ScenariosData> {
        return mmnetData.data.mmnet_data.getOrNull(mmnet_index)?.areas?.areas_data?.getOrNull(
            area_index
        )?.area?.scenarios_data ?: emptyList()
    }

}
