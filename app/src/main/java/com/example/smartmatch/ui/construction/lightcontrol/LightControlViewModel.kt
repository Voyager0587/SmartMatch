package com.example.smartmatch.ui.construction.lightcontrol

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: LightControlViewModel
 * @author: Voyager
 * @description: 照明控制的ViewModel
 * @date:  2023/10/3 14:20
 * @version 1.0
 **/
class LightControlViewModel:ViewModel() {
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null
    var mmnetData: LiveData<Result<MMNetResponse>>? = null

    fun getMMNetData(){
        mmnetData=repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }


}