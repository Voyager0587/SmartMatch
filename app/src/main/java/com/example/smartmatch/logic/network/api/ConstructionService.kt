package com.example.smartmatch.logic.network.api


import com.example.smartmatch.logic.model.MMNetResponse

import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
import com.example.smartmatch.logic.network.model.CheckCTData
import com.example.smartmatch.logic.network.model.CollectionScenariosResponse
import com.example.smartmatch.logic.network.model.ResponseMessage
import com.example.smartmatch.logic.network.model.ScenarioResponse
import com.example.smartmatch.logic.network.model.SceneCreationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @className: ConstructionService
 * @author: Voyager
 * @description:施工界面API
 * @date:  2023/9/20 20:08
 * @version 1.0
 **/
interface ConstructionService {

    @GET("/mmnet/data")
    fun getMMNetAllData(): Call<MMNetResponse>

    /**
     * 根据MMNetID获取集合场景响应
     * @param id 场景ID
     * @return CollectionScenariosResponse对象的Call
     */
    @GET("/control/area/{id}")
    fun getCollectionScenarioByNetId(@Path("id") id: Int): Call<CollectionScenariosResponse>

    /**
     * 根据区域ID获取T数据
     * @param id 区域ID
     * @return SceneCreationResponse对象的Call
     */
    @GET("/mmnet/scenario/light/{id}")
    fun getTByAreaId(@Path("id") id: Int): Call<SceneCreationResponse>

    /**
     * 根据区域ID获取Light数据
     * @param id 区域ID
     * @return SceneCreationResponse对象的Call
     */
    @GET("/mmnet/scenario/light/{id}")
    fun getLightByAreaId(@Path("id") id: Int): Call<SceneCreationResponse>

    /**
     * 关闭场景
     * @param scene 场景关闭辅助对象
     * @return ResponseMessage对象的Call
     */
    @POST("/instructions/scenario/off")
    fun closeScene(@Body scene: SceneCloseHelper): Call<ResponseMessage>

    /**
     * 创建新场景
     * @param id 场景ID
     * @param name 场景名称
     * @return ResponseMessage对象的Call
     */
    @POST("/mmnet/scenario/{id}")
    fun createNewScenario(@Field("id") id: Int, @Header("name") name: String): Call<ResponseMessage>

    /**
     * 创建新区域
     * @param id 区域ID
     * @param area 区域创建辅助对象
     * @return ResponseMessage对象的Call
     */
    @POST("/mmnet/area/{id}")
    fun createNewArea(@Path("id") id: Int, @Body area: AreaCreationHelper): Call<ResponseMessage>

    /**
     * 对mmnet中打开scenario操作的指令
     * @param scenario 要控制的场景的id和percentage的List
     * @return null
     */
    @POST("/instructions/scenario/on")
    fun instructScenario(@Body scenario: ScenarioResponse): Call<ResponseMessage>

    /**
     * 根据区域ID查找C数据
     * @param id 区域ID
     * @return MMNetResponse对象的Call
     */
    @GET("/mmnet/area/c/{id}")
    fun findC(@Path("id") id: String): Call<MMNetResponse>

    /**
     * 设置新场景
     * @param checkCTData 检查CT数据对象
     * @return MMNetResponse对象
     */
    @POST("/mmnet/scenario/{id}")
    suspend fun setnewscenario(@Body checkCTData: CheckCTData): MMNetResponse

}