package com.example.smartmatch.logic.model
import com.google.gson.annotations.SerializedName


/**
 * @className: MMNetResponse
 * @author: Voyager
 * @description: MMNet数据类
 * @date:  2023/9/22 20:53
 * @version 1.0
 **/


data class MMNetResponse(
    val code: Int,
    val `data`: Data,
    val msg: Any
)

data class Data(
    val mmnet_data: List<MmnetData>,
    val mmnet_num: Int
)

data class MmnetData(
    val C: C,
    val admin_id: String,
    val areas: Areas,
    val control: Control,
    val mmnet_id: Int,
    val mmnet_name: String
)

data class C(
    val c_data: List<CData>,
    val c_num: Int
)

data class Areas(
    val area_num: Int,
    val areas_data: List<AreasData>
)

data class Control(
    val control_data: List<Any>,
    val control_num: Int
)

data class CData(
    val id: Int
)

data class AreasData(
    val area: Area
)

data class Area(
    val area_c: List<AreaC>,
    val area_light: List<AreaLight>,
    val id: Int,
    val name: String,
    val scenarios_data: List<ScenariosData>,
    val scenarios_num: Int
)

data class AreaC(
    val id: Int
)

data class AreaLight(
    val c_id: Int,
    val light_id: Int,
    val original_id: Int
)

data class ScenariosData(
    val child_scenario: List<ChildScenario>,
    val id: Int,
    val light_data: List<LightData>,
    val light_num: Int,
    val name: String,
    val required_percentage: Double
)

data class ChildScenario(
    val child_scenario: List<ChildScenarioX>,
    val definitional_percentage: Double,
    val id: Int,
    val name: String
)

data class LightData(
    val definitional_percentage: Double,
    val executable_percentage: Double,
    val light_id: Int
)

data class ChildScenarioX(
    val definitional_percentage: Double,
    val id: Int,
    val name: String
)

    //data class  findtdata(val lightList:List<light>,val ift:Int)
    data class LightDatat(val id :Int )

//data class light(val id: Int,
//    val percentage:Double
//)
data class lightoff(val id: Int)

data class LightOffBody(
    @SerializedName("light")
    val light: List<Light>
)

data class Light(
    @SerializedName("id")
    val id: Int
)

data class MmnetScenarioLight(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: TData,
    @SerializedName("msg")
    val msg: String?
)
data class LightReponse(
    val mmnetScenarioLight: MmnetScenarioLight,
    val switch:Int
)

data class TData(
    @SerializedName("light_data")
    val lightData: List<LightDataT>,
    @SerializedName("light_num")
    val lightNum: Int
)

data class LightDataT(
    @SerializedName("id")
    val id: Int
)


data class TPrecentageBody(
    @SerializedName("light")
    val light: List<Light1>
)

data class Light1(
    @SerializedName("id")
    val id: Int,
    @SerializedName("percentage")
    val percentage: Int
)
data class CheckCTData(
    @SerializedName("name")
    val name: String,
    @SerializedName("scenario_definitional_percentage")
    val scenarioDefinitionalPercentage: Double,
    @SerializedName("scenario_light")
    val scenarioLight: List<CheckCTData>
)
data class checkctRepose(
    val data: List<CheckCTData>
)

data class ScenarioLight(
    @SerializedName("light_definitional_percentage")
    val lightDefinitionalPercentage: Double,
    @SerializedName("light_id")
    val lightId: Int
)