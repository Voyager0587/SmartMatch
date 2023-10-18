package com.example.smartmatch.chart

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

class TData: LitePalSupport() {
    @Column(nullable = false)
    var t_id:Int=0
    var t_precent:Int=0
    override fun toString(): String {
        return "TData(t_id=$t_id, t_precent=$t_precent)"
    }

    override fun save(): Boolean {
        var s:Boolean=super.save()
        setId(getId())
        update(baseObjId)

        return s
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