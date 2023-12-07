package com.example.smartmatch.logic.model.helper

import com.google.gson.annotations.SerializedName


data class UpCTAllHelper(
    @SerializedName("C")
    val c: List<C1>,
    @SerializedName("Light")
    val light: List<Light>,
    @SerializedName("name")
    val name: String
)

data class C1(
    @SerializedName("id")
    val id: Int
)

data class Light(
    @SerializedName("light_id")
    val lightId: Int
)