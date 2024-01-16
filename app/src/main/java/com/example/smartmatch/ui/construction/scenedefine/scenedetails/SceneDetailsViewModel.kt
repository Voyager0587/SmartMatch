package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.helper.ItemHelper
import com.example.smartmatch.logic.model.helper.LightBean
import com.example.smartmatch.logic.model.helper.ScenarioLight
import com.example.smartmatch.logic.network.model.DetailData
import com.example.smartmatch.logic.network.model.ScenarioDetailResponse
import com.example.smartmatch.logic.network.model.SubSceneResponse
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: SceneDetailsViewModel
 * @author: Voyager
 * @description: 场景详情的ViewModel
 * @date:  2024/1/10 15:44
 * @version 1.0
 **/
class SceneDetailsViewModel : ViewModel() {
    var scenarioId = -1
    var scenarioAddedList=ArrayList<ItemHelper>()
    val lightList=ArrayList<ItemHelper>()
    private val repository = Repository
    internal var constructionListener: ConstructionListener? = null
    lateinit var sceneDetailsData: LiveData<Result<ScenarioDetailResponse>>
        private set
    var subScenarioDetailsData: LiveData<Result<SubSceneResponse>>? = null
    var scenarioDetailResponse: DetailData? = null
    var mmnetData: LiveData<Result<MMNetResponse>>? = null
    fun switchFragment(fragment: Fragment,tag: String) {
        constructionListener?.switchFragment(fragment, tag)
    }

    fun getScenarioDetail(id: Int) {
        sceneDetailsData = repository.getScenarioDetails(id)
        constructionListener?.progressReturnData(sceneDetailsData)
    }

    //获取场景下的子场景
    fun getSubScenarioDetails(id: String){
        subScenarioDetailsData=repository.getSubScenarioDetails(id)
    }
    fun getMMNetData() {
        mmnetData = repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }

    fun letAllLightsOff(id: String) {
        repository.letAllLightsOff(id)
    }
    fun deleteLight(id: String, light: LightBean) {
        repository.deleteLight(id, light)
    }

    fun submitLight(id: String, light: LightBean) {
        repository.submitLight(id, light)
    }
    fun getLights(id: String) {
        repository.getLights(id)
    }
    fun flicker(flicker: LightBean) {
        repository.flicker(flicker)
    }
    fun addLight(id: String) {
        repository.addLight(id)
    }
    fun switchScenario(id: String, scenario: ScenarioLight){
        repository.switchScenario(id, scenario)
    }
    fun deleteScenario(id: String, scenario: ScenarioLight) {
        repository.deleteScenario(id, scenario)
    }
    fun getAddScenario(id: String) {
        val response= repository.getAddScenario(id)
        constructionListener?.processAddSceneResponse(response)
    }
    fun addScenario(id: String, scenario: ScenarioLight) {
        repository.addScenario(id, scenario)
    }
}