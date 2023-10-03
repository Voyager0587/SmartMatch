package com.example.smartmatch.ui.construction.adapter

import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.AppRecycleItemWideTextBinding
import com.example.smartmatch.logic.model.ScenariosData

/**
 * @className: SceneAdapter
 * @author: Voyager
 * @description: 集合场景显示场景列表
 * @date:  2023/10/2 22:47
 * @version 1.0
 **/
class SceneAdapter : BaseAdapter<ScenariosData, AppRecycleItemWideTextBinding>() {
    override fun AppRecycleItemWideTextBinding.onBindViewHolder(
        bean: ScenariosData,
        position: Int
    ) {
        tvName.text = bean.name
    }

}