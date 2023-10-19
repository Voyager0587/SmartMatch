//package com.example.smartmatch.ui.checkCT
//
//import android.annotation.SuppressLint
//import com.example.smartmatch.base.activity.BaseAdapter
//import com.example.smartmatch.databinding.ItemLightControlScenarioBinding
//import com.example.smartmatch.logic.model.CheckCTData
//import com.example.smartmatch.logic.model.ScenariosData
//import com.example.smartmatch.ui.construction.lightcontrol.LightControlScenarioFragment
//import com.example.smartmatch.ui.view.MultiButton
//
//class CheckCTAdapter (private val activity:CheckCTActivity) :
//    BaseAdapter<CheckCTData, ItemLightControlScenarioBinding>() {
//
//    //储存点击选中的Scenario
//    var checkctdata = ArrayList<CheckCTData>()
//        private set
//    override fun ItemLightControlScenarioBinding.onBindViewHolder(
//        @SuppressLint("RecyclerView") bean: CheckCTData,
//        position: Int
//    ) {
//        //   multiBtn.text=bean.name
//        multiBtn.text(bean.name, bean.scenarioDefinitionalPercentage, bean.scenarioLight.size)
//        multiBtn.setOnClickListener(object : MultiButton.OnClickListener{
//            override fun onTitleClick() {
//                if(multiBtn.judge)
//                    checkctdata.add(bean)
//                else
//                    checkctdata.remove(bean)
//
//            }
//
//        })
//    }
//
//}
