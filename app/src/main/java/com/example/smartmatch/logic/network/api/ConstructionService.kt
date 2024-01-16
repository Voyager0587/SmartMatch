package com.example.smartmatch.logic.network.api


import com.example.smartmatch.logic.model.MMNetResponse

import com.example.smartmatch.logic.model.helper.AreaCreationHelper
import com.example.smartmatch.logic.model.helper.LightBean
import com.example.smartmatch.logic.model.helper.ScenarioLight
import com.example.smartmatch.logic.model.helper.SceneCloseHelper
import com.example.smartmatch.logic.network.model.CanAddScenesResponse
import com.example.smartmatch.logic.network.model.CollectionScenariosResponse
import com.example.smartmatch.logic.network.model.LightDataResponse
import com.example.smartmatch.logic.network.model.ResponseMessage
import com.example.smartmatch.logic.network.model.ScenarioDetailResponse
import com.example.smartmatch.logic.network.model.ScenarioResponse
import com.example.smartmatch.logic.network.model.SceneCreationResponse
import com.example.smartmatch.logic.network.model.SubSceneResponse
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

    /**
     * 获取场景详细信息
     * @param id 场景ID
     */
    @GET("/modify/scenario/details/{id}")
    fun getScenarioDetials(@Path("id") id: String): Call<ScenarioDetailResponse>

    /**
     * AllOff
     * 关闭所有灯的接口
     */
    @GET("/instructions/all_off/{id}")
    fun letAllLightsOff(@Path("id") id: String): Call<MMNetResponse>

    /**
     * 删除灯的接口
     */
    @POST("/modify/scenario/light/delete/{id}")
    fun deleteLight(@Path("id") id: String,@Body light: LightBean): Call<MMNetResponse>

    /**
     * 提交修改后场景中灯的定义百分比并启动
     */
    @POST("/modify/scenario/light/{id}")
    fun submitLight(@Path("id") id: String,@Body light: LightBean ): Call<MMNetResponse>

    /**
     * 保存灯修改后的数据（只进行数据的保存，不启动）
     */
    @POST("/modify/scenario/light/save/{id}")
    fun saveLight(@Path("id") id: String): Call<MMNetResponse>


    /**
     *为scenario绑定灯的界面返回有哪些灯可选
     */
    @GET("/mmnet/scenario/light/{id}")
    fun getLights(@Path("id") id: String): Call<LightDataResponse>

    /**
     * 让特定的灯闪
     */
    @POST("/mmnet/light/flicker")
    fun flicker(@Body flicker: LightBean): Call<MMNetResponse>

    /**
     * 添加灯
     */
    @POST("/modify/scenario/light/add/{id}")
    fun addLight(@Path("id") id: String): Call<MMNetResponse>

    /**
     * 保存修改后场景的信息
     */
    @POST("/modify/scenario/child/save/{id}")
    fun saveScenario(@Path("id") id: String, @Body scenario: ScenarioLight): Call<MMNetResponse>

    /**
     * 调试场景的亮暗
     */
    @POST("/modify/scenario/child/{id}")
    fun switchScenario(@Path("id") id: String, @Body scenario: ScenarioLight): Call<MMNetResponse>

    /**
     * 删除场景
     */
    @POST("/modify/scenario/child/delete/{id}")
    fun deleteScenario(@Path("id") id: String, @Body scenario: ScenarioLight): Call<MMNetResponse>

    /**
     * 返回可添加的场景
     */
    @GET("/modify/scenario/child/add/{id}")
    fun getAddScenario(@Path("id") id: String): Call<CanAddScenesResponse>

    /**
     * 返回场景的子场景
     */
    @GET("/modify/scenario/child/details/{id}")
    fun getSubScenarioDetails(@Path("id") id: String): Call<SubSceneResponse>
    /**
     * 添加场景
     */
    @POST("/modify/scenario/child/add/{id}")
    fun addScenario(@Path("id") id: String, @Body scenario: ScenarioLight): Call<MMNetResponse>
}