package com.example.smartmatch.chart


class TData {

    var t_id:Int=0
    var t_precent:Int=0
    override fun toString(): String {
        return "TData(t_id=$t_id, t_precent=$t_precent)"
    }


    fun getId():Int{
        return t_id
    }
    fun setId(id:Int){
        this.t_id=id
    }
    fun getPrecent():Int{
        return t_precent
    }
    fun setPrecent(precent:Int){
        this.t_precent=precent
    }
}