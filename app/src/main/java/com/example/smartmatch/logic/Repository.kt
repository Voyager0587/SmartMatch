package com.example.smartmatch.logic


import androidx.lifecycle.liveData

import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.FindTHelper
import com.example.smartmatch.logic.model.helper.LightBean
import com.example.smartmatch.logic.model.helper.ScenarioLight
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
import com.example.smartmatch.logic.model.helper.UpCTAllHelper
import com.example.smartmatch.logic.network.NetworkCenter
import com.example.smartmatch.logic.network.model.ScenarioResponse
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @className: Repository
 * @author: Voyager
 * @description: Repository
 * @date:  2023/9/22 21:31
 * @version 1.0
 **/
object Repository {

    fun getCollectionScenarioByNetId(id: Int)= fire(Dispatchers.IO){
        val response=NetworkCenter.getCollectionScenarioByNetId(id)
        run{
            Result.success(response)
        }
    }

    fun getMMNetData() = fire(Dispatchers.IO) {
        val response = NetworkCenter.getMMNetData()
        run {
            Result.success(response)
        }
    }

    fun getTByAreaId(id: Int) = fire(Dispatchers.IO) {
        val response = NetworkCenter.getTByAreaId(id)
        run {
            Result.success(response)
        }
    }

    fun getLightByAreaId(id: Int) = fire(Dispatchers.IO) {
        val response = NetworkCenter.getLightByAreaId(id)
        run {
            Result.success(response)
        }
    }

    fun closeScene(sceneCloseHelper: SceneCloseHelper) = fire(Dispatchers.IO) {
        val response = NetworkCenter.closeScene(sceneCloseHelper)
        run {
            Result.success(response)
        }
    }

    fun instructScenario(scenario: ScenarioResponse) = fire(Dispatchers.IO) {
        val response = NetworkCenter.instructScenario(scenario)
        run {
            Result.success(response)
        }
    }

    fun getScenarioDetails(id: Int) = fire(Dispatchers.IO) {
        val response = NetworkCenter.getScenarioDetails(id.toString())
        run {
            Result.success(response)
        }
    }
    fun letAllLightsOff(id: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.letAllLightsOff(id)
        run {
            Result.success(response)
        }
    }

    fun deleteLight(id: String, light: LightBean) = fire(Dispatchers.IO) {
        val response = NetworkCenter.deleteLight(id, light)
        run {
            Result.success(response)
        }
    }

    fun submitLight(id: String, light: LightBean) = fire(Dispatchers.IO) {
        val response = NetworkCenter.submitLight(id, light)
        run {
            Result.success(response)
        }
    }

    fun saveLight(id: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.saveLight(id)
        run {
            Result.success(response)
        }
    }

    fun getLights(id: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.getLights(id)
        run {
            Result.success(response)
        }
    }

    fun flicker(flicker: LightBean) = fire(Dispatchers.IO) {
        val response = NetworkCenter.flicker(flicker)
        run {
            Result.success(response)
        }
    }

    fun addLight(id: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.addLight(id)
        run {
            Result.success(response)
        }
    }

    fun saveScenario(id: String, scenario: ScenarioLight) = fire(Dispatchers.IO) {
        val response = NetworkCenter.saveScenario(id, scenario)
        run {
            Result.success(response)
        }
    }

    fun switchScenario(id: String, scenario: ScenarioLight) = fire(Dispatchers.IO) {
        val response = NetworkCenter.switchScenario(id, scenario)
        run {
            Result.success(response)
        }
    }

    fun deleteScenario(id: String, scenario: ScenarioLight) = fire(Dispatchers.IO) {
        val response = NetworkCenter.deleteScenario(id, scenario)
        run {
            Result.success(response)
        }
    }

    fun getAddScenario(id: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.getAddScenario(id)
        run {
            Result.success(response)
        }
    }

    fun getSubScenarioDetails(id: String)= fire(Dispatchers.IO) {
        val response = NetworkCenter.getSubScenarioDetails(id)
        run {
            Result.success(response)
        }
    }

    fun getScenarioDetails(id: String)= fire(Dispatchers.IO) {
        val response = NetworkCenter.getScenarioDetails(id)
        run {
            Result.success(response)
        }
    }
    fun addScenario(id: String, scenario: ScenarioLight) = fire(Dispatchers.IO) {
        val response = NetworkCenter.addScenario(id, scenario)
        run {
            Result.success(response)
        }
    }

    fun createNewArea(id: Int, areaCreationHelper: AreaCreationHelper) = fire(Dispatchers.IO) {
        val response = NetworkCenter.createNewArea(id, areaCreationHelper)
        run {
            Result.success(response)
        }
    }

    fun login(username: String, password: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.login(username, password)
        run {
            Result.success(response)
        }
    }

    fun findCData(id:Int) = fire(Dispatchers.IO) {
        val response=NetworkCenter.findCdata(id)
        run {
            Result.success(response)
        }
    }
    fun findTData(findTHelper: FindTHelper)=fire(Dispatchers.IO){
        val response=NetworkCenter.findTData(findTHelper)
        run{
            Result.success(response)
        }
    }
    fun upCTData(upCTAllHelper: UpCTAllHelper)= fire(Dispatchers.IO){
        val response=NetworkCenter.upCTData(upCTAllHelper)
        run {
            Result.success(response)
        }
    }





    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}