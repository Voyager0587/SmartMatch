package com.example.smartmatch.ui.construction.clusterscene

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className: CascadeControlViewModel
 * @author: Voyager
 * @description: 集群场景的ViewModel
 * @date:  2023/9/30 19:46
 * @version 1.0
 **/
class CollectionSceneViewModel :ViewModel() {
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null

    var mmnetData: LiveData<Result<MMNetResponse>>? = null

    fun getMMNetData(){
        mmnetData=repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }
}