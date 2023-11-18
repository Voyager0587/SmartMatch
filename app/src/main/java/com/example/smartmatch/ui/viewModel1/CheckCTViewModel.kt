package com.example.smartmatch.ui.viewModel1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository

import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.network.model.CheckCTData
import com.example.smartmatch.ui.construction.ConstructionListener

class CheckCTViewModel :ViewModel(){
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null
    var mmnetData: LiveData<Result<MMNetResponse>>? = null
        private set
    lateinit var checkCTData: CheckCTData
    fun getMMNetData(){
        mmnetData=repository.getMMNetData()
        constructionListener?.processMMNetData(mmnetData!!)
    }

    fun sendMessage(){
        repository.setnewscenario(checkCTData)
        //发送完,scenarios数据就要清空null
    }
//
//    fun closeScene(){
//        repository.closeScene()
//    }

    fun checkctdata( checkCTData: CheckCTData){
        val result=repository.setnewscenario(checkCTData)
        //constructionListener?.processResponse(result)
    }



}