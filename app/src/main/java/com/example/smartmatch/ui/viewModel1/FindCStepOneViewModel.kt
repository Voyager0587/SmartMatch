package com.example.smartmatch.ui.viewModel1

import android.view.View
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData
import com.example.smartmatch.chart.FindCTBtnParams
class FindCStepOneViewModel : ViewModel() {
   var CNumber: MutableLiveData<Int> = MutableLiveData()
   var layout: MutableLiveData<Array<Int>> = MutableLiveData()
   var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
   var identifiedTheCList: MutableList<FindCTBtnParams<View>> = ArrayList()
   var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()

}