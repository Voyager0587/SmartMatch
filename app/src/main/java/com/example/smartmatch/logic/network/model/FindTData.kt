package com.example.smartmatch.logic.network.model
import com.google.gson.annotations.SerializedName


data class FindTData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: TData,
    @SerializedName("msg")
    val msg: Any
)

data class TData(
    @SerializedName("light_data")
    val lightData: List<LightDataT>,
    @SerializedName("num")
    val num: Int
)

data class LightDataT(
    @SerializedName("c_id")
    val cId: Int,
    @SerializedName("light_id")
    val lightId: Int,
    @SerializedName("original_id")
    val originalId: Int
)
