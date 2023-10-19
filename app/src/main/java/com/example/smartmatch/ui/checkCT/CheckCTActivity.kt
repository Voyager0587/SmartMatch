//package com.example.smartmatch.ui.checkCT
//
//import android.annotation.SuppressLint
//import android.widget.SeekBar
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.smartmatch.SmartApplication.Companion.context
//import com.example.smartmatch.base.activity.BaseActivity
//import com.example.smartmatch.databinding.ActivityCheckctBinding
//import com.example.smartmatch.logic.model.CheckCTData
//import com.example.smartmatch.logic.model.checkctRepose
//import com.example.smartmatch.ui.findT.step_one.FindTStepOneFragment
//import com.example.smartmatch.ui.viewModel1.FindTStepOneViewModel
//
//class CheckCTActivity(fragment: FindTStepOneFragment): BaseActivity<ActivityCheckctBinding>(){
//    private val mViewModel by lazy {
//        ViewModelProvider(
//            fragment,
//            ViewModelProvider.NewInstanceFactory()
//        )[FindTStepOneViewModel::class.java]
//    }
//    var sb_progress=0
//    private var list: List<CheckCTData> = mutableListOf()
//    private lateinit var adapter: CheckCTAdapter
//    override fun ActivityCheckctBinding.initBindingView() {
//        binding.checkctModel=mViewModel
//        binding.sbProgressCheckct?.setOnSeekBarChangeListener(object :
//            SeekBar.OnSeekBarChangeListener {
//            @SuppressLint("SetTextI18n")
//            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
//                // SeekBar的进度发生改变时执行的操作
//                binding.checkctPgText?.text="$progress%"
//
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar) {
//                // 用户开始拖动SeekBar时执行的操作
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar) {
//                // 用户停止拖动SeekBar时执行的操作
//                sb_progress = seekBar.progress
//
//            }
//
//        })
//        initRecyclerList()
//        getInstructionScenario()
//
//
//    }
//    private fun initRecyclerList() {
//
//        list=mViewModel.currentlight
//        adapter= CheckCTAdapter(this)
//        binding.rvChecktvdata?.layoutManager=LinearLayoutManager(context)
//        binding.rvChecktvdata?.adapter=adapter
//        adapter.setData(list)
//        }
//
//
//    private fun getInstructionScenario(): checkctRepose {
//        val checkctlist = adapter.checkctdata
//        val scenarioList = ArrayList<CheckCTData>()
//        for (checkct in checkctlist) {
//            val checklist =
//                CheckCTData(checkct.name,
//                    checkct.scenarioDefinitionalPercentage,checkct.scenarioLight
//                )
//            scenarioList.add(checklist)
//        }
//        return checkctRepose(scenarioList)
//    }
//
//
//}