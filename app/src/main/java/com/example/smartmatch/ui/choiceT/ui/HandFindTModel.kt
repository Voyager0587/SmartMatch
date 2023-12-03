package com.example.smartmatch.ui.choiceT.ui
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.helper.FindTHelper
import com.example.smartmatch.ui.construction.ConstructionListener

class HandFindTModel : ViewModel() {
    var CNumber: MutableLiveData<Int> = MutableLiveData()
    var layout: MutableLiveData<Array<Int>> = MutableLiveData()
    var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var identifiedTheCList: MutableList<FindCTBtnParams<View>> = ArrayList()
    var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null
    fun getTData(findTHelper: FindTHelper){
        val result= repository.findTData(findTHelper)
        constructionListener?.findTResponse(result)
    }


}