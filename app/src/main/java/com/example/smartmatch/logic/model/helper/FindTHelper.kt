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
data class PostLight1(
    @SerializedName("light")
    val light: List<Light1>
)

data class Light1(
    @SerializedName("id")
    val id: Int
)