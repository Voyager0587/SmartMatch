package com.example.smartmatch.ui.construction.areadefine

import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: AreaDefineViewModel
 * @author: Voyager
 * @description: 场景定义的ViewModel
 * @date:  2023/9/20 17:43
 * @version 1.0
 **/
class AreaDefineViewModel:ViewModel() {
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null


    fun getMMNetData(){
    val result=repository.getMMNetData()
    constructionListener?.getMMNetData(result)
    }
}