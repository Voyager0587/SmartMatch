package com.example.smartmatch.logic.model.helper

/**
 * @className: areaCreationHelper
 * @author: Voyager
 * @description: 新建Area的帮助类
 * @date:  2023/10/16 15:05
 * @version 1.0
 **/
class AreaCreationHelper{
    private var name: String=""
    private var C: List<CBean?>? = ArrayList<CBean>()
    private var Light: List<LightBean?>? = ArrayList<LightBean>()

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        if (name != null) {
            this.name = name
        }
    }

    fun getC(): List<CBean?>? {
        return C
    }

    fun setC(C: List<CBean?>?) {
        this.C = C
    }

    fun getLight(): List<LightBean?>? {
        return Light
    }

    fun setLight(Light: List<LightBean?>?) {
        this.Light = Light
    }

    class CBean {
        /**
         * id : 22
         */
        var id = 0
    }

    class LightBean {
        /**
         * light_id : 4
         */
        var light_id = 0
    }
}
