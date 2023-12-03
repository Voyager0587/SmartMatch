package com.example.smartmatch.logic.network.model

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