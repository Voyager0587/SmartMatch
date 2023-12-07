package com.example.smartmatch.ui.construction.adapter

import android.view.View
import com.example.smartmatch.base.activity.BaseAdapter
import com.example.smartmatch.databinding.ItemClusterControlBinding
import com.example.smartmatch.logic.model.helper.CollectionScenarioHelper
import com.example.smartmatch.logic.model.helper.SceneCategory

/**
 * @className: CollectionScenarioAdapter
 * @author: Voyager
 * @description: 集群场景中选择场景组成的集合场景中的适配器
 * @date:  2023/10/11 17:14
 * @version 1.0
 **/
class ClusterScenarioAdapter :
    BaseAdapter<CollectionScenarioHelper, ItemClusterControlBinding>() {
    override fun ItemClusterControlBinding.onBindViewHolder(
        bean: CollectionScenarioHelper,
        position: Int
    ) {
        textNetDisplay.text=bean.netName
        textPercentDisplay.text=bean.brightnessPercent.toString()
        when(bean.flags){
            SceneCategory.SceneCategoryType.BASIC_SCENARIOS->
            {
                textAreaDisplay.text=bean.areaName
                textScenarioDisplay.text=bean.scenarioName

            }
            SceneCategory.SceneCategoryType.GATHERING_SCENES->{
                textScenario.visibility=View.GONE
                textScenarioDisplay.visibility=View.GONE
                textArea.text="集合场景"
                textAreaDisplay.text=bean.collectionScenesName
            }
            SceneCategory.SceneCategoryType.CLUSTER_SCENARIO->{

            }
            // !!!  树状显示那里还是用RecyclerView来显示吧，后面总会这样用的

        }



    }

}