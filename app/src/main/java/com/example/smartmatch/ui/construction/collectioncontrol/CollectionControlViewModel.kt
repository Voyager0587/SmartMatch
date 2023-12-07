package com.example.smartmatch.ui.construction.collectioncontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.helper.ScenarioHelper
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: ClusterControlViewModel
 * @author: Voyager
 * @description: 集合控制ViewModel
 * @date:  2023/11/15 21:21
 * @version 1.0
 **/
class CollectionControlViewModel : ViewModel() {
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null
    var netId:Int=-1
    var scenarioHelper:ScenarioHelper?=null
     var mmnetData: LiveData<Result<MMNetResponse>>? = null


    fun getMMNetData(){
        mmnetData=repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }

    fun getCollectionScenarioData() {
        val result= repository.getCollectionScenarioByNetId(netId)
        constructionListener?.processCollectionScenariosResponse(result)
    }


}