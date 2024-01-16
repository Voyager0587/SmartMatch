package com.example.smartmatch.logic.network.model

/**
 * @className: CanAddScenesResponse
 * @author: Voyager
 * @description: TODO
 * @date:  2024/1/15 21:57
 * @version 1.0
 **/
data class CanAddScenesResponse(
    val code: Int,
    val `data`: List<Data2>?,
    val msg: String?
)

data class Data2(
    val desc: Any,
    val name: String,
    val scenario_id: Int
)