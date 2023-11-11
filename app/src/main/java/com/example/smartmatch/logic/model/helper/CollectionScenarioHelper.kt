package com.example.smartmatch.logic.model.helper

/**
 * @className: CollectionScenarioHelper
 * @author: Voyager
 * @description: 便于集合场景创建的工具类，包括场景所属的MMNET,Area,Scene,light_percent
 * @date:  2023/10/11 17:21
 * @version 1.0
 **/
data class CollectionScenarioHelper(
    /**
     * MMNet名称
     */
    var netName: String,
    /**
     * Area名称
     */
    var areaName: String,
    /**
     * Scene名称
     */
    var scenarioName: String,
    /**
     * 亮度百分比
     */
    var brightnessPercent: Float,
    /**
     * 集合场景名称
     */
    var collectionScenesName: String,
    /**
     * 判断场景类型
     */
    var flags: SceneCategory.SceneCategoryType
)
class SceneCategory{
    enum class SceneCategoryType{
        /**
         * 基础场景
         */
        BASIC_SCENARIOS,
        /**
         * 集合场景
         */
        GATHERING_SCENES
    }
}




