package com.example.smartmatch.logic.network.model
import com.google.gson.annotations.SerializedName


class FindCData (
    val code:Int,
    val data:Data1,
    val msg:String
)
data class Data1(
    val c_data:List<Cid>,
    val c_num:Int
)
data class Cid(
    val id: Int
)
data class choicetdata(

    val code: Int,

    val `data`: Data2,

    val msg: Any
)

data class Data2(
    val lightData: List<LightData>,
    val lightNum: Int
)
