package com.example.smartmatch.ui.construction.lightcontrol

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentLightControlSceneBinding
import com.example.smartmatch.logic.model.ScenariosData
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
) : BaseFragment<FragmentLightControlSceneBinding>() {
    private var list: List<ScenariosData> = mutableListOf()
    private val mViewModel: LightControlViewModel by lazy {
        ViewModelProvider(
            fragment,
            ViewModelProvider.NewInstanceFactory()
        )[LightControlViewModel::class.java]
    }

    override fun FragmentLightControlSceneBinding.initBindingView() {
        binding.viewModel = mViewModel

        val mmnetData = mViewModel.mmnetData?.value?.getOrNull()

        list = mmnetData?.data?.mmnet_data?.getOrNull(mmnet_index)?.areas?.areas_data?.getOrNull(
            area_index
        )?.area?.scenarios_data ?: emptyList()

        val adapter = LightControlScenarioAdapter(this@LightControlScenarioFragment)
        recyclerScenario.layoutManager = LinearLayoutManager(requireContext())
        recyclerScenario.adapter = adapter
        adapter.setData(list)
    }
}
