package com.example.smartmatch.ui.construction.adapter


import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
import com.example.smartmatch.logic.model.ScenariosData
import com.example.smartmatch.ui.construction.lightcontrol.LightControlScenarioFragment

/**
 * @className: LightControlScenario
 * @author: Voyager
 * @description: 照明控制的场景的Adapter
 * @date:  2023/10/3 19:45
 * @version 1.0
 **/
class LightControlScenarioAdapter(private val fragment: LightControlScenarioFragment) :
    BaseAdapter<ScenariosData, ItemLightControlScenarioBinding>() {
    override fun ItemLightControlScenarioBinding.onBindViewHolder(
        bean: ScenariosData,
        position: Int
    ) {
        //   multiBtn.text=bean.name
        multiBtn.text(bean.name)

    }

}