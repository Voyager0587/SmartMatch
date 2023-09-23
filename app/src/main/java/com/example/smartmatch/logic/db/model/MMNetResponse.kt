package com.example.smartmatch.logic.db.model

import org.litepal.crud.LitePalSupport

/**
 * @className: MMNetResponse
 * @author: Voyager
 * @description: MMNet数据类
 * @date:  2023/9/22 20:53
 * @version 1.0
 **/
data class MMNetResponse(
    var code: Int,
    val data: Data,
    var msg: Any
):LitePalSupport()

data class Data(
    val mmnet_data: List<MmnetData>,
    val mmnet_num: Int
):LitePalSupport()

data class MmnetData(
    val C: C,
    val admin_id: String,
    val areas: Areas,
    val mmnet_id: Int,
    val mmnet_name: String
):LitePalSupport()

data class C(
    val c_data: List<CData>,
    val c_num: Int
):LitePalSupport()

data class Areas(
    val area_num: Int,
    val areas_data: List<AreasData>
):LitePalSupport()

data class CData(
    val id: Int
):LitePalSupport()

data class AreasData(
    val area: Area
):LitePalSupport()

data class Area(
    val area_c: List<AreaC>,
    val area_light: List<AreaLight>,
    val id: Int,
    val name: String,
    val scenarios_data: List<ScenariosData>,
    val scenarios_num: Int
):LitePalSupport()

data class AreaC(
    val id: Int
):LitePalSupport()

data class AreaLight(
    val c_id: Int,
    val light_id: Int
):LitePalSupport()

data class ScenariosData(
    val id: Int,
    val light_data: List<LightData>,
    val light_num: Int,
    val name: String
):LitePalSupport()

data class LightData(
    val light_id: Int,
    val light_wattage: Int,
    val percentage: Double
):LitePalSupport()