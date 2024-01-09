package com.example.smartmatch.ui.findT.step_one

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.network.model.FindCData
import com.example.smartmatch.logic.network.model.choicetdata
import com.example.smartmatch.ui.construction.ConstructionListener

class FindTStepOneViewModel : ViewModel() {
    var CNumber: MutableLiveData<Int> = MutableLiveData()
    var layout: MutableLiveData<Array<Int>> = MutableLiveData()
    var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var chooseTheCList = mutableListOf<FindCTBtnParams<View>>()
    var auditTCList = mutableListOf<FindCTBtnParams<View>>()
    var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
    var tdata: LiveData<Result<choicetdata>>?=null
    private  val repository= Repository
    internal var constructionListener: ConstructionListener?=null

    fun getchoicetdata(id: Int){
        tdata= repository.choicetdata(id)
        constructionListener?.choicetdata(tdata!!)
    }


}
