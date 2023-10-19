package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.model.LightOffBody
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetScenarioLight
import com.example.smartmatch.logic.model.TPrecentageBody
import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
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

    @GET("/mmnet/scenario/light/{id}")
    fun getTByAreaId(@Path("id") id: Int): Call<SceneCreationResponse>

    @GET("/mmnet/scenario/light/{id}")
    fun getLightByAreaId(@Path("id") id: Int): Call<SceneCreationResponse>

    @POST("/instructions/scenario/off")
    fun closeScene(@Body scene:SceneCloseHelper):Call<ResponseMessage>

    @POST("/mmnet/scenario/{id}")
    fun createNewScenario(@Field("id") id: Int, @Header("name") name: String)


    @POST("/mmnet/area/{id}")
    fun createNewArea(@Path("id") id: Int,@Body area: AreaCreationHelper):Call<ResponseMessage>

    /**
     * @description 对mmnet中打开scenario操作的指令
     * @param scenario 要控制的场景的id和percentage的List
     * @return null
     */
    @POST("/instructions/scenario/on")
    fun instructScenario(@Body scenario: ScenarioResponse):Call<ResponseMessage>
    @GET("/mmnet/area/c/{id}")
    fun  findC(@Path("id") id: String):Call<MMNetResponse>
    @GET("/mmnet/scenario/light/{id}")
    suspend fun findT(@Path("id") id: Int): MmnetScenarioLight
    @POST("instructions/light/on")
    suspend  fun postLightOn(@Body requestBody: TPrecentageBody): MMNetResponse
    @POST("/instructions/light/off")
    suspend fun checkok(@Body ok: LightOffBody):MMNetResponse
}