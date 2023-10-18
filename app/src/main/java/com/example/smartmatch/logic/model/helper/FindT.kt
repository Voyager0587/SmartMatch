package com.example.smartmatch.logic.model.helper

class FindT {
    private var name: String=""
    private var num:Int=0
    private var T: List<TBean?>? = ArrayList<TBean>()
    private var Light: List<LightBean?>? = ArrayList<LightBean>()
    fun getnum():Int?{
        return num
    }
    fun setnum(num:Int?){
        if(num!=null){
            this.num=num
        }
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        if (name != null) {
            this.name = name
        }
    }

    fun getT(): List<TBean?>? {
        return T
    }

    fun setT(T: List<TBean?>?) {
        this.T = T
    }

    fun getLight(): List<LightBean?>? {
        return Light
    }

    fun setLight(Light: List<LightBean?>?) {
        this.Light = Light
    }

    class TBean {
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