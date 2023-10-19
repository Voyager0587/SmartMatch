package com.example.smartmatch.logic.network

import android.util.Log
import com.example.smartmatch.logic.model.CheckCTData
import com.example.smartmatch.logic.model.LightOffBody
import com.example.smartmatch.logic.model.TPrecentageBody
import com.example.smartmatch.logic.model.User
import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
import com.example.smartmatch.logic.network.api.ConstructionService
import com.example.smartmatch.logic.network.api.PersonService
import com.example.smartmatch.logic.network.model.ScenarioResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @className NetworkCenter
 * @description NetworkCenter
 * @author Voyager
 * @date 2023/9/22 21:15
 */

object NetworkCenter {

    // private val authServer = ServiceCreator.create<AuthService>()
    private val constructionServer = ServiceCreator.create<ConstructionService>()
    private val personServer = ServiceCreator.create<PersonService>()

    /**
     * Construction
     */

    /**
     * 获取MMNet全部数据
     */
    suspend fun getMMNetData() = constructionServer.getMMNetAllData().await()
    suspend fun getTByAreaId(id:Int)= constructionServer.getTByAreaId(id).await()
    suspend fun getLightByAreaId(id:Int)= constructionServer.getLightByAreaId(id).await()
    suspend fun closeScene(scene:SceneCloseHelper)= constructionServer.closeScene(scene).await()

    /**
     * 照明控制--控制场景
     */
    suspend fun instructScenario(scenario: ScenarioResponse) =
        constructionServer.instructScenario(scenario).await()

    /**
     * 创建Area
     */
    suspend fun createNewArea(id:Int,areaCreationHelper: AreaCreationHelper)
        = constructionServer.createNewArea(id,areaCreationHelper).await()
    suspend fun checkyulan(checkyulan: TPrecentageBody) = constructionServer.postLightOn(checkyulan)
    suspend fun checkok(ok: LightOffBody)= constructionServer.checkok(ok)
    suspend fun setnewscenario(checkCTData: CheckCTData)= constructionServer.setnewscenario(checkCTData)

    /**
     * 登录
     */
    suspend fun login(username: String, password: String) =
        personServer.login(User(username.toInt(), password)).await()

    /**
     * 找C
     */
    suspend fun findCid(id: String)=constructionServer.findC(id).await()

    /**
     * 找T
     */
    suspend fun findTid(id: Int)= constructionServer.findT(id)




    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
//                    ToDo: delete this
                    Log.e("NetworkCenter", response.toString())
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}

