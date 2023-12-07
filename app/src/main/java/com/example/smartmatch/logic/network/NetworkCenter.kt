package com.example.smartmatch.logic.network

import android.util.Log
import com.example.smartmatch.logic.model.User
import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.FindTHelper
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
import com.example.smartmatch.logic.model.helper.UpCTAllHelper
import com.example.smartmatch.logic.network.api.ConstructionService
import com.example.smartmatch.logic.network.api.FindCService
import com.example.smartmatch.logic.network.api.FindTService
import com.example.smartmatch.logic.network.api.PersonService
import com.example.smartmatch.logic.network.api.UpCTAll
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
    private val findCData=ServiceCreator.create<FindCService>()
    private val findTService=ServiceCreator.create<FindTService>()
    private val upCTService=ServiceCreator.create<UpCTAll>()

    /**
     * Construction
     */
    /**
     * 建筑物
     */

    /**
     * 获取MMNet全部数据
     */
    suspend fun getMMNetData() = constructionServer.getMMNetAllData().await()

    /**
     * 根据地区ID获取T数据
     */
    suspend fun getTByAreaId(id: Int) = constructionServer.getTByAreaId(id).await()

    /**
     * 根据地区ID获取灯光数据
     */
    suspend fun getLightByAreaId(id: Int) = constructionServer.getLightByAreaId(id).await()

    /**
     * 关闭场景
     */
    suspend fun closeScene(scene: SceneCloseHelper) = constructionServer.closeScene(scene).await()

    /**
     * 根据MMNetID获取集合场景
     */
    suspend fun getCollectionScenarioByNetId(id: Int) =
        constructionServer.getCollectionScenarioByNetId(id).await()

    /**
     * 照明控制--控制场景
     */
    suspend fun instructScenario(scenario: ScenarioResponse) =
        constructionServer.instructScenario(scenario).await()

    /**
     * 创建Area
     */
    suspend fun createNewArea(id: Int, areaCreationHelper: AreaCreationHelper) =
        constructionServer.createNewArea(id, areaCreationHelper).await()

    /**
     * 登录
     */
    suspend fun login(username: String, password: String) =
        personServer.login(User(username.toInt(), password)).await()

    /**
     * 找C
     */
    suspend fun findCdata(id: Int)= findCData.getCData(id).await()
    /**
     * 找T
     */
    suspend fun findTData(findTData: FindTHelper)= findTService.postTData(findTData).await()
    /**
     * 创建Area的时候提交数据
     */
    suspend fun upCTData(upCTAllHelper: UpCTAllHelper)= upCTService.postupctData(upCTAllHelper).await()

    private suspend fun <T> Call<T>.await(): T {
        // 创建一个挂起函数，用于等待Call对象的响应并返回结果
        return suspendCoroutine { continuation ->
            // 注册一个回调函数，当Call对象有响应时调用
            enqueue(object : Callback<T> {
                // 当Call对象有响应时调用
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    // 获取响应的body数据
                    val body = response.body()
                    // ToDo: 删除此行
                    Log.e("NetworkCenter", response.toString())
                    // 如果body数据不为空，通过挂起函数的continuation继续执行后续代码并传递body数据
                    if (body != null) continuation.resume(body)
                    // 如果body数据为空，抛出一个运行时异常
                    else continuation.resumeWithException(RuntimeException("response body is null"))
                }

                // 当Call对象发生失败时调用
                override fun onFailure(call: Call<T>, t: Throwable) {
                    // 抛出一个运行时异常
                    continuation.resumeWithException(t)
                }
            })
        }
    }

}

