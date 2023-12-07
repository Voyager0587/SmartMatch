package com.example.smartmatch.logic.model.helper

import com.google.gson.annotations.SerializedName
data class FindTHelper(
    @SerializedName("c")
    val c: List<C>
)

data class C(
    @SerializedName("id")
    var id: Int
)
data class choiceT(
    var t:Int
)
data class cT(
    var id:Int
)