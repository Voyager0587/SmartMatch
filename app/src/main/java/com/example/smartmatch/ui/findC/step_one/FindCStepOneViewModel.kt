package com.example.smartmatch.ui.findC.step_one

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.logic.Repository

import com.example.smartmatch.logic.model.helper.C
import com.example.smartmatch.logic.model.helper.FindTHelper
import com.example.smartmatch.logic.network.model.FindCData
import com.example.smartmatch.ui.construction.ConstructionListener

class FindCStepOneViewModel : ViewModel() {
   var CNumber: MutableLiveData<Int> = MutableLiveData()
   var layout: MutableLiveData<Array<Int>> = MutableLiveData()
   var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var identifiedTheCList: MutableList<FindCTBtnParams<View>> = ArrayList()
   var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
   lateinit var trueC: C
   private  val repository=Repository
   internal var constructionListener:ConstructionListener?=null
   var cdata: LiveData<Result<FindCData>>?=null

   // var response = findC(findCid)  // 在这里传入 ID 参数
   fun getCData(id: Int){
      cdata= repository.findCData(id)
      constructionListener?.findCData(cdata!!)
   }
   fun getTData(findTHelper: FindTHelper){
      val result= repository.findTData(findTHelper)
      constructionListener?.findTResponse(result)
   }

}