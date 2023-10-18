package com.example.smartmatch.ui.viewModel1

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.logic.network.model.Checkyulan
import com.example.smartmatch.ui.construction.ConstructionListener
import kotlin.properties.Delegates

class FindTStepOneViewModel : ViewModel() {
    var CNumber: MutableLiveData<Int> = MutableLiveData()
    var layout: MutableLiveData<Array<Int>> = MutableLiveData()
    var btnsParams1: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams2: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var btnsParams3: MutableLiveData<MutableList<FindCTBtnParams<View>>> = MutableLiveData()
    var chooseTheCList = mutableListOf<FindCTBtnParams<View>>()
    var auditTCList = mutableListOf<FindCTBtnParams<View>>()
    var currentlyDeterminedCBtn: MutableLiveData<FindCTBtnParams<View>> = MutableLiveData()
    val findtdata=MutableLiveData<FindT>()

    private val repository = Repository
    var findtid=""
    var t_num=""
    internal var constructionListener: ConstructionListener?=null
    var findt:LiveData<Result<MMNetResponse>>?=null
    val findT=FindT()
    val findTid="116412487"
    var checkyulan by Delegates.notNull<Int>()
    var ok by Delegates.notNull<Int>()
    fun getFIndT(){
        findt=repository.findT()
        constructionListener?.processMMNetData(findt!!)
    }

    fun sendMessage(){
        repository.checkyulan(checkyulan)
        repository.checkOk(ok)
        //发送完,scenarios数据就要清空null
    }

    fun checkyulan(checkyulan: Int){
        val result=repository.checkyulan(checkyulan)
        constructionListener?.processMMNetData(result)
    }
    fun ok(ok:Int){
        val result=repository.checkOk(ok)
        constructionListener?.processMMNetData(result)
    }



}
