package com.example.smartmatch.ui.construction.scenedefine

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.network.model.SceneCreationResponse
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: SceneDefineViewModel
 * @author: Voyager
 * @description: 场景定义的ViewModel
 * @date:  2023/9/30 13:49
 * @version 1.0
 **/
class SceneDefineViewModel : ViewModel() {
    private val repository = Repository
    internal var constructionListener: ConstructionListener? = null

    var mmnetData: LiveData<Result<MMNetResponse>>? = null
    var tData: LiveData<Result<SceneCreationResponse>>? = null
    fun getMMNetData() {
        mmnetData = repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }

    fun getTByAreaId(areaId: Int){
        val temp =  repository.getTByAreaId(1)
        val temp2=repository.getLightByAreaId(areaId)
//        tData=temp
        tData=temp2
        constructionListener?.processTData(tData!!)

    }

}