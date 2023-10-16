package com.example.smartmatch.ui.viewModel1

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.chart.FindCTBtnParams

class FindTStepOneViewModel : ViewModel() {
    var CNumber: MutableLiveData<Int> = MutableLiveData()
    var layout: MutableLiveData<Array<Int>> = MutableLiveData()
    var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var chooseTheCList = mutableListOf<FindCTBtnParams<View>>()
    var auditTCList = mutableListOf<FindCTBtnParams<View>>()
    var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
}
