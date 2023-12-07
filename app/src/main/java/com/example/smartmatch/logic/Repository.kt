package com.example.smartmatch.logic


import androidx.lifecycle.liveData

import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.FindTHelper
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