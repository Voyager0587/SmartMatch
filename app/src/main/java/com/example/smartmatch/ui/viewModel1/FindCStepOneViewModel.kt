package com.example.smartmatch.ui.viewModel1

import android.view.View
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.Repository.findC
import com.example.smartmatch.ui.construction.ConstructionListener

class FindCStepOneViewModel : ViewModel() {
   var CNumber: MutableLiveData<Int> = MutableLiveData()
   var layout: MutableLiveData<Array<Int>> = MutableLiveData()
   var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var identifiedTheCList: MutableList<FindCTBtnParams<View>> = ArrayList()
   var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
   private  val repository=Repository
   internal var constructionListener:ConstructionListener?=null
    val findCid="116326259"
    var response = findC(findCid)  // 在这里传入 ID 参数


}