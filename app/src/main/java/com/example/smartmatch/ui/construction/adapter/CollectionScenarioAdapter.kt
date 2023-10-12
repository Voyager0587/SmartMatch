package com.example.smartmatch.ui.construction.adapter

import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemCollectionScenarioBinding
import com.example.smartmatch.logic.model.helper.CollectionScenarioHelper

/**
 * @className: CollectionScenarioAdapter
 * @author: Voyager
 * @description: 集合场景中选择场景组成的集合场景中的适配器
 * @date:  2023/10/11 17:14
 * @version 1.0
 **/
class CollectionScenarioAdapter :
    BaseAdapter<CollectionScenarioHelper, ItemCollectionScenarioBinding>() {
    override fun ItemCollectionScenarioBinding.onBindViewHolder(
        bean: CollectionScenarioHelper,
        position: Int
    ) {
        tvMmnetName.text = bean.mmnet_name
        tvAreaName.text = bean.area_name
        tvSceneName.text = bean.scene_name
        tvLightPercent.text = bean.light_percent.toString()
        tvRank.text = position.toString()
    }

}