package com.example.smartmatch.ui.construction.adapter

import android.annotation.SuppressLint
import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
import com.example.smartmatch.logic.model.helper.ItemHelper
import com.example.smartmatch.ui.view.MultiButton

/**
 * @className: LightDetailsAdapter
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/13 22:37
 * @version 1.0
 **/
class LightDetailsAdapter() :
    com.example.smartmatch.base.activity.BaseAdapter<ItemHelper, ItemLightControlScenarioBinding>() {

    //储存点击选中的Scenario
    var existList = ArrayList<ItemHelper>()//存放选中的item
        private set
    var adjustPercentage: Float = 100.0F

    override fun ItemLightControlScenarioBinding.onBindViewHolder(
        @SuppressLint("RecyclerView") bean: ItemHelper,
        position: Int
    ) {
        if (multiBtn.judge) {
            //val temp=bean.required_percentage*(adjustPercentage/100)
            val str: String = String.format("%.2f", (adjustPercentage / 100))
            multiBtn.item_id = bean.id
            multiBtn.text(bean.name, str.toDouble(), bean.id)

        } else {
            multiBtn.item_id = bean.id
            multiBtn.text(bean.name, bean.percentage, bean.id)
        }

        // ? 这里通过add（记得判断重复点击，查找重复？），removeAt来操作
        // ! id记录，但是percentage要X一下发过去

        multiBtn.setOnClickListener(object : MultiButton.OnClickListener {
            override fun onTitleClick() {
                if (multiBtn.judge)
                    existList.add(bean)
                else
                    existList.remove(bean)

            }

        })
    }

}