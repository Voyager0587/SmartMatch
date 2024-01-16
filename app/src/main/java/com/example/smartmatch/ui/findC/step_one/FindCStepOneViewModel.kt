package com.example.smartmatch.ui.findC.step_one

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.chart.checkTAll
import com.example.smartmatch.logic.Repository

import com.example.smartmatch.logic.model.helper.C
import com.example.smartmatch.logic.model.helper.FindTHelper
import com.example.smartmatch.logic.network.model.FindCData
import com.example.smartmatch.ui.construction.ConstructionListener

class FindCStepOneViewModel : ViewModel() {
   val CNumber: MutableLiveData<Int> = MutableLiveData()
   val layout: MutableLiveData<Array<Int>> = MutableLiveData()
   val btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   val btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   val btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   val identifiedTheCList: MutableList<FindCTBtnParams<View>> = ArrayList()
   val currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
   lateinit var trueC: C
   private  val repository=Repository
   internal var constructionListener:ConstructionListener?=null
   var cdata: LiveData<Result<FindCData>>?=null
   var t_num:Int=0;
   var name_area=" "
   var ttt: MutableLiveData<Int>? = MutableLiveData()

   val list=ArrayList<checkTAll>()
   var liveDataList: MutableLiveData<List<checkTAll>> = MutableLiveData()

   fun updateList(list: List<checkTAll>) {
      this.list.clear()
      this.list.addAll(list)

      liveDataList.value = this.list
      Log.d("HandFindTModel", "LiveDataList updated: $list")
   }

   fun getCData(id: Int){
      cdata= repository.findCData(id)
      constructionListener?.findCData(cdata!!)
   }
   fun getTData(findTHelper: FindTHelper){
      val result= repository.findTData(findTHelper)
      constructionListener?.findTResponse(result)
   }

}