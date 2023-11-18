package com.example.smartmatch.ui.construction.adapter

import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemChooseSingleBinding
import com.example.smartmatch.logic.model.helper.ScenarioHelper
import com.example.smartmatch.ui.view.ItemButton

/**
 * @className: ClusterScenarioAdapter
 * @author: Voyager
 * @description: 集合场景适配器
 * @date:  2023/11/17 14:38
 * @version 1.0
 **/
class CollectionScenarioAdapter :
    BaseAdapter<ScenarioHelper, ItemChooseSingleBinding>() {
    var netId=-1
    private set
    override fun ItemChooseSingleBinding.onBindViewHolder(bean: ScenarioHelper, position: Int) {
        btnItem.net_id=bean.id
        btnItem.text(bean.name)

        btnItem.setOnClickListener(object : ItemButton.OnClickListener {
            override fun onTitleClick() {
                netId=btnItem.net_id
            }

        })
    }
}