package com.example.smartmatch.ui.construction.adapter


import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
import com.example.smartmatch.logic.model.ScenariosData
import com.example.smartmatch.ui.construction.lightcontrol.LightControlScenarioFragment
import com.example.smartmatch.ui.toast

/**
 * @className: LightControlScenario
 * @author: Voyager
 * @description: 照明控制的场景的Adapter
 * @date:  2023/10/3 19:45
 * @version 1.0
 **/
class LightControlScenarioAdapter(private val fragment: LightControlScenarioFragment) :
    BaseAdapter<ScenariosData, ItemLightControlScenarioBinding>() {

    //储存点击选中的Scenario
    var scenarioList = ArrayList<ScenariosData>()
    private set


    override fun ItemLightControlScenarioBinding.onBindViewHolder(
        bean: ScenariosData,
        position: Int
    ) {
        //   multiBtn.text=bean.name
        multiBtn.text(bean.name, bean.required_percentage, bean.id)
        // ? 这里通过add（记得判断重复点击，查找重复？），removeAt来操作
        // ! id记录，但是percentage要X一下发过去
        multiBtn.setOnClickListener{
            if(multiBtn.judge)
                scenarioList.add(bean)
            else
                scenarioList.remove(bean)
            "Adapter:点击了item".toast()
        }
    }

}