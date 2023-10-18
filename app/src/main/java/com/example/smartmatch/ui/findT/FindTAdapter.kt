package com.example.smartmatch.ui.findT

import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
import com.example.smartmatch.logic.model.ScenariosData
import com.example.smartmatch.logic.model.light
import com.example.smartmatch.ui.findT.step_one.FindTStepOneFragment
import com.example.smartmatch.ui.toast

class FindTAdapter(private  val fragment: FindTStepOneFragment) :BaseAdapter<light,ItemLightControlScenarioBinding>(){
    var checktid = ArrayList<light>()
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
                checktid.add(bean)
            else
                checktid.remove(bean)
            "Adapter:点击了item".toast()
        }
    }

    override fun ItemLightControlScenarioBinding.onBindViewHolder(bean: light, position: Int) {

    }

}