package com.example.smartmatch.logic.network.model

/**
 * @className: CollectionScenariosResponse
 * @author: Voyager
 * @description: 获取当前net下的集合场景
 * @modifier:
 * @date:  2023/11/17 19:34
 * @version 1.0
 **/
data class CollectionScenariosResponse(
    val code: Int,
    val `data`: List<ScenariosData>,
    val msg: Any
)

data class ScenariosData(
    val description: String,
    val id: Int,
    val mm_net_name: String,
    val name: String
)