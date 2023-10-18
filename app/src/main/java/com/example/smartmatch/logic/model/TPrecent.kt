package com.example.smartmatch.logic.model

import com.example.smartmatch.chart.TData
import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport


class TPrecent:LitePalSupport() {
    @Column(unique = true, defaultValue = "unkown")
    val id_t:Int=0
    val precent:Int=0
    private val tData: List<TData> = ArrayList<TData>()



}