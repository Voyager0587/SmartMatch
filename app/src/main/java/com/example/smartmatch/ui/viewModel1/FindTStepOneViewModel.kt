package com.example.smartmatch.ui.viewModel1

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.logic.model.CheckCTData
import com.example.smartmatch.logic.model.LightOffBody
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetScenarioLight
import com.example.smartmatch.logic.model.TPrecentageBody
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
    var currentchooseBt= mutableListOf<Int>()
    var currentlight= mutableListOf< CheckCTData>()
    private var lightOffBody :LightOffBody? = null
    private var tPrecentageBody:TPrecentageBody?=null
    private val repository = Repository
    internal var constructionListener: ConstructionListener?=null
    var findt:LiveData<Result<MmnetScenarioLight>>?=null
    val findT=FindT()

    var checkyulan by Delegates.notNull<Int>()
    var ok by Delegates.notNull<Int>()
    fun getFIndT(id: Int): LiveData<Result<MmnetScenarioLight>> {
        return repository.findT(id)///.getOrNull()?.data?.lightNum
    }

    fun sendMessage(){

        //repository.checkyulan(checkyulan)
        lightOffBody?.let { repository.checkOk(it) }
        //发送完,scenarios数据就要清空null
    }
    fun sendLightList(){
        tPrecentageBody?.let{repository.checkyulan(tPrecentageBody!!)}
    }
    fun saveLightList(tPrecentageBody: TPrecentageBody){

    }

    fun save(lightOffBody: LightOffBody){
        this.lightOffBody = lightOffBody
    }

    fun checkyulan(checkyulan: TPrecentageBody){
        val result=repository.checkyulan(checkyulan)
        constructionListener?.processMMNetData(result)
    }




}
