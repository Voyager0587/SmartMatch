package com.example.smartmatch.logic.model
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @className: MMNetResponse
 * @author: Voyager
 * @description: MMNet数据类
 * @date:  2023/9/22 20:53
 * @version 1.0
 **/
@Entity(tableName = "mmnet_response")
data class MMNetResponse(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var code: Int,
    var msg: String
)

@Entity(tableName = "data")
data class Data(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val mmnet_num: Int
)

@Entity(tableName = "mmnet_data")
data class MmnetData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val admin_id: String,
    val mmnet_id: Int,
    val mmnet_name: String
)

@Entity(tableName = "c")
data class C(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val c_num: Int
)

@Entity(tableName = "areas")
data class Areas(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val area_num: Int
)

@Entity(tableName = "c_data")
data class CData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val c_id: Int
)

@Entity(tableName = "areas_data")
data class AreasData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)

@Entity(tableName = "area")
data class Area(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val scenarios_num: Int
)

@Entity(tableName = "area_c")
data class AreaC(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val area_id: Long,
    val c_id: Int
)

@Entity(tableName = "area_light")
data class AreaLight(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val area_id: Long,
    val c_id: Int,
    val light_id: Int
)

@Entity(tableName = "scenarios_data")
data class ScenariosData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val area_id: Long,
    val name: String,
    val light_num: Int
)

@Entity(tableName = "light_data")
data class LightData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val scenarios_id: Long,
    val light_id: Int,
    val light_wattage: Int,
    val percentage: Double
)
