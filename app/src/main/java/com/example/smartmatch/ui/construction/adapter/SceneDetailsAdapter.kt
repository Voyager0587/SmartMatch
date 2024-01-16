package com.example.smartmatch.ui.construction.adapter

import android.annotation.SuppressLint
import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
import com.example.smartmatch.logic.model.helper.ItemHelper
import com.example.smartmatch.ui.view.MultiButton

/**
 * @className: SceneDetailsAdapter
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/16 12:38
 * @version 1.0
 **/
class SceneDetailsAdapter : BaseAdapter<ItemHelper, ItemLightControlScenarioBinding>() {

    //储存点击选中的Scenario
    var existList = ArrayList<ItemHelper>()//存放选中的item
        private set
    var adjustPercentage: Float = 100.0F

    override fun ItemLightControlScenarioBinding.onBindViewHolder(
        @SuppressLint("RecyclerView") bean: ItemHelper,
        position: Int
    ) {
        multiBtn.item_id = bean.id
        multiBtn.text(bean.name, -1.0, bean.id)

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