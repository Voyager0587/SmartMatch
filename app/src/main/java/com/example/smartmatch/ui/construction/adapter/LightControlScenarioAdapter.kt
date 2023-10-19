package com.example.smartmatch.ui.construction.adapter


import android.annotation.SuppressLint
import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
import com.example.smartmatch.logic.model.ScenariosData
import com.example.smartmatch.ui.construction.lightcontrol.LightControlScenarioFragment
import com.example.smartmatch.ui.view.MultiButton

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
    var adjustPercentage:Float=100.0F

    override fun ItemLightControlScenarioBinding.onBindViewHolder(
        @SuppressLint("RecyclerView") bean: ScenariosData,
        position: Int
    ) {
        if(multiBtn.judge){
            //val temp=bean.required_percentage*(adjustPercentage/100)
            val str :String = String.format("%.2f",(adjustPercentage/100))
            multiBtn.text(bean.name, str.toDouble(), bean.id)
        }else{
            multiBtn.text(bean.name, bean.required_percentage, bean.id)
        }

        // ? 这里通过add（记得判断重复点击，查找重复？），removeAt来操作
        // ! id记录，但是percentage要X一下发过去

        multiBtn.setOnClickListener(object :MultiButton.OnClickListener{
            override fun onTitleClick() {
                if(multiBtn.judge)
                    scenarioList.add(bean)
                else
                    scenarioList.remove(bean)

            }

        })
    }

}