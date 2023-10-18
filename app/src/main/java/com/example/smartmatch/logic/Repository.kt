package com.example.smartmatch.logic


import androidx.lifecycle.liveData
import com.example.smartmatch.logic.model.LightOffBody
import com.example.smartmatch.logic.model.TPrecentageBody
import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.FindT
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


     fun getMMNetData() = fire(Dispatchers.IO) {
        val response = NetworkCenter.getMMNetData()
        run {
            Result.success(response)
        }
    }

    fun closeScene()=fire(Dispatchers.IO) {
        val response = NetworkCenter.closeScene()
        run {
            Result.success(response)
        }
    }

    fun instructScenario( scenario: ScenarioResponse)= fire(Dispatchers.IO){
        val response=NetworkCenter.instructScenario(scenario)
        run{
            Result.success(response)
        }
    }

     fun createNewArea(id: Int,areaCreationHelper: AreaCreationHelper) = fire(Dispatchers.IO) {
        val response = NetworkCenter.createNewArea(id,areaCreationHelper)
        run {
            Result.success(response)
        }
    }

    fun login(username: String, password: String) = fire(Dispatchers.IO) {
        val response=NetworkCenter.login(username, password)
        run {
            Result.success(response)
        }
    }
    fun findC(id: String)= fire(Dispatchers.IO){
        val response=NetworkCenter.findCid(id)
         run{
             Result.success(response)
         }
    }

    fun findT(id: Int)= fire(Dispatchers.IO){
        val response=NetworkCenter.findTid(id)
        run{
            Result.success(response)
        }
    }
    fun checkyulan(light:TPrecentageBody) = fire(Dispatchers.IO) {
        val response = NetworkCenter.checkyulan(light)
        run {
            Result.success(response)
        }
    }
    fun checkOk(ok: LightOffBody)= fire(Dispatchers.IO){
        val response=NetworkCenter.checkok(ok)
        run{
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