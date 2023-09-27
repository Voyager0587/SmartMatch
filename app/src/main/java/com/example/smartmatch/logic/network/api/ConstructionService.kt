package com.example.smartmatch.logic.network.api

import com.example.smartmatch.logic.model.MMNetResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * @className: ConstructionService
 * @author: Voyager
 * @description:施工界面API
 * @date:  2023/9/20 20:08
 * @version 1.0
 **/
interface ConstructionService {


    @GET("")
    fun getMMNetControllers(){}

    @GET("mmnet/data")
    fun getMMNetAllData():Call<MMNetResponse>

    @GET("")
    fun getMMNetArea(){}
}