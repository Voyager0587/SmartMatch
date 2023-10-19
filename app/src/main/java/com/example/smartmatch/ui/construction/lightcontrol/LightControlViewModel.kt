package com.example.smartmatch.ui.construction.lightcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
import com.example.smartmatch.logic.network.model.ScenarioResponse
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: LightControlViewModel
 * @author: Voyager
 * @description: 照明控制的ViewModel
 * @date:  2023/10/3 14:20
 * @version 1.0
 **/
class LightControlViewModel:ViewModel() {
    private val repository = Repository
    var seekBarPercentage:String="100"
    internal var constructionListener: ConstructionListener?=null
    var mmnetData: LiveData<Result<MMNetResponse>>? = null
    private set
    lateinit var scenarios:ScenarioResponse


    fun getMMNetData(){
        mmnetData=repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }

    fun sendMessage(){
        repository.instructScenario(scenarios)
        //发送完,scenarios数据就要清空null
    }

    fun closeScene(sceneCloseHelper: SceneCloseHelper){
        val result= repository.closeScene(sceneCloseHelper)
        constructionListener?.processSceneCloseResponse(result)
    }

    fun instructScenario( scenario: ScenarioResponse){
        val result=repository.instructScenario(scenario)
        constructionListener?.processResponse(result)
    }


}