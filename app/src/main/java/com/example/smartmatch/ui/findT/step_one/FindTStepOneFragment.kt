package com.example.smartmatch.ui.findT.step_one
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.eazylight.ui.find_C.step_one.adapter.RvIdentifiedTheCListAdapter
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.util.CTNumberUtils
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.databinding.FragmentFindTStepOneBinding
import com.example.smartmatch.logic.model.helper.FindT
//import com.example.smartmatch.ui.checkCT.CheckCTActivity
import com.example.smartmatch.ui.construction.ConstructionListener

class FindTStepOneFragment (var idd:Int,var name:String): BaseFragment<FragmentFindTStepOneBinding>(),ConstructionListener {
    private lateinit var chooseAdapter: RvIdentifiedTheCListAdapter
    private lateinit var hasAuditAdapter: RvIdentifiedTheCListAdapter
    val viewFindCTBtnParams = FindCTBtnParams<TextView>()
    private val viewModel: FindTStepOneViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[FindTStepOneViewModel::class.java]
    }

//    var idi:Int = 0
//    val intent=Intent(requireActivity(),CheckCTActivity::class.java)
//    private var  t_percentage = 0
//    var current=viewModel.currentlight



    override fun FragmentFindTStepOneBinding.initBindingView() {
        binding.vmT=viewModel
        binding.clickT=Click()

        binding.swFindt.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//                if(isChecked){
//                    idi=1
//                }
//                if(!isChecked){
//                    idi=0
//                }
            }

        })
        binding.ok.setOnClickListener{
//            if(idi==1){
//                viewModel.sendMessage()
//            }
        }
        //sb_clickListener()
    }
//    fun sb_clickListener(){
//        binding.sbFindt.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                binding.tvTprogress.text= progress.toString()+"%"
//                //tdata.setPrecent(progress)
//
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                t_percentage=seekBar!!.progress
//                if(idi ==1){
//                    viewModel.sendMessage()
//                    viewModel.sendLightList()
//                }
//            }
//
//        })
//    }
    inner class Click {
        fun chose(view: View) {
            val value = viewModel.currentlyDeterminedCBtn.value
            if (value != null) {
                val listValue = viewModel.chooseTheCList
                //var listid=viewModel.currentchooseBt
                for (vp in listValue)
                    if (vp.START_CID == value.START_CID)
                        return
                val newV = FindCTBtnParams<View>()
                newV.START_CID = value.START_CID
                //listid.add(viewFindCTBtnParams.view?.tag as Int)
                listValue.add(newV)
                chooseAdapter.notifyItemInserted(listValue.size)

               // current.add(idd, CheckCTData(name,t_percentage.toDouble(),viewModel.currentlight.toList()))




            }
        }

        fun chooseAllNotAudit(view: View) {
            val findCTBtnParamsList = ArrayList<FindCTBtnParams<View>>()
            for (i in 1 until viewModel.CNumber.value!!) {
                val newV = FindCTBtnParams<View>()
                newV.START_CID = i
                findCTBtnParamsList.add(newV)
            }
            findCTBtnParamsList.removeAll(viewModel.auditTCList)
            findCTBtnParamsList.removeAll(viewModel.chooseTheCList)
            viewModel.chooseTheCList.addAll(findCTBtnParamsList)
            chooseAdapter.notifyDataSetChanged()
        }

        fun confirm(view: View) {
            viewModel.auditTCList.addAll(viewModel.chooseTheCList)
            hasAuditAdapter.notifyDataSetChanged()
            viewModel.chooseTheCList.clear()
            chooseAdapter.notifyDataSetChanged()
        }
    }



    override fun initDataBeforeView() {
        viewModel.btnsParams1.value = ArrayList()
        viewModel.btnsParams2.value = ArrayList()
        viewModel.btnsParams3.value = ArrayList()
    }

    override fun observerDataStateUpdateAction() {
        viewModel.CNumber.observe(this) { integer ->
            val layout = CTNumberUtils.count(integer)
            if (layout == null) {
                return@observe
            }
            if (layout.size >= 1) {
                binding.ll1.visibility = View.VISIBLE
            }
            if (layout.size >= 2) {
                binding.ll2.visibility = View.VISIBLE
            }
            if (layout.size >= 3) {
                binding.ll3.visibility = View.VISIBLE
            }
            viewModel.layout.value = layout
        }
        viewModel.layout.observe(this) { layout ->
            if (layout == null) {
                return@observe
            }
            if (layout.size >= 1) {
                for (i in 0 until layout[0]) {
                    val lp =
                        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)

                    val linearLayout = LinearLayout(requireActivity())
                    linearLayout.orientation = LinearLayout.VERTICAL
                    linearLayout.gravity = Gravity.CENTER_HORIZONTAL

                    val btnLL = LinearLayout(requireActivity())
                    btnLL.orientation = LinearLayout.HORIZONTAL

                    val fill1 = TextView(requireActivity())
                    val fill2 = TextView(requireActivity())
                    val btn = TextView(requireActivity())
                    btn.gravity = Gravity.CENTER
                    btn.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    btnLL.addView(fill1, lp)
                    btnLL.addView(btn)
                    btnLL.addView(fill2, lp)

                    val textView = TextView(requireActivity())
                    textView.text = "T-T"
                    textView.setTextColor(Color.BLACK)
                    textView.gravity = Gravity.CENTER

                    linearLayout.addView(btnLL)
                    linearLayout.addView(textView)

                    binding.ll1.addView(linearLayout, lp)
                }
            }
            if (layout.size >= 2) {
                for (i in 0 until layout[1]) {
                    val lp =
                        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)

                    val linearLayout = LinearLayout(requireActivity())
                    linearLayout.orientation = LinearLayout.VERTICAL
                    linearLayout.gravity = Gravity.CENTER_HORIZONTAL

                    val btnLL = LinearLayout(requireActivity())
                    btnLL.orientation = LinearLayout.HORIZONTAL

                    val fill1 = TextView(requireActivity())
                    val fill2 = TextView(requireActivity())
                    val btn = TextView(requireActivity())
                    btn.gravity = Gravity.CENTER
                    btn.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    btnLL.addView(fill1, lp)
                    btnLL.addView(btn)
                    btnLL.addView(fill2, lp)

                    val textView = TextView(requireActivity())
                    textView.text = "T-T"
                    textView.setTextColor(Color.BLACK)
                    textView.gravity = Gravity.CENTER

                    linearLayout.addView(btnLL)
                    linearLayout.addView(textView)

                    binding.ll2.addView(linearLayout, lp)
                }
            }
            if (layout.size >= 3) {
                for (i in 0 until layout[2]) {
                    val lp =
                        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)

                    val linearLayout = LinearLayout(requireActivity())
                    linearLayout.orientation = LinearLayout.VERTICAL
                    linearLayout.gravity = Gravity.CENTER_HORIZONTAL

                    val btnLL = LinearLayout(requireActivity())
                    btnLL.orientation = LinearLayout.HORIZONTAL

                    val fill1 = TextView(requireActivity())
                    val fill2 = TextView(requireActivity())
                    val btn = TextView(requireActivity())
                    btn.gravity = Gravity.CENTER
                    btn.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    btnLL.addView(fill1, lp)
                    btnLL.addView(btn)
                    btnLL.addView(fill2, lp)

                    val textView = TextView(requireActivity())
                    textView.text = "T-T"
                    textView.setTextColor(Color.BLACK)
                    textView.gravity = Gravity.CENTER

                    linearLayout.addView(btnLL)
                    linearLayout.addView(textView)

                    binding.ll3.addView(linearLayout, lp)
                }
            }
        }
    }

    override fun initDataAfterView() {

        viewModel.CNumber.value =127//viewModel.getFIndT(idd).value?.getOrNull()?.data?.lightNum
    }

    fun initViewParams() {
        val let = viewModel.layout.value?.let { layout ->
            viewModel.CNumber.value?.let { cNumber ->
                if (viewModel.layout.value == null) return
                if (viewModel.CNumber.value == null) return
                if (viewModel.layout.value!!.size >= 1) {
                    val cid1 = CTNumberUtils.countCID(viewModel.CNumber.value!!, viewModel.layout.value!![0])
                    for (i in 0 until viewModel.layout.value!![0]) {
                        val btn = ((binding.ll1.getChildAt(i) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(1) as TextView
                        val tv = (binding.ll1.getChildAt(i) as LinearLayout).getChildAt(1) as TextView
                        val viewFindCTBtnParams = FindCTBtnParams<TextView>()
                        viewFindCTBtnParams.view = btn
                        viewFindCTBtnParams.tv = tv
                        val location = IntArray(2)
                        btn.getLocationOnScreen(location)
                        viewFindCTBtnParams.rawX = location[0].toFloat()
                        viewFindCTBtnParams.rawY = location[1].toFloat()
                        viewFindCTBtnParams.width = btn.width.toFloat()
                        viewFindCTBtnParams.height = btn.height.toFloat()
                        viewFindCTBtnParams.id = viewModel.btnsParams1.value!!.size + 1
                        viewFindCTBtnParams.START_CID = cid1[0][i]
                        viewFindCTBtnParams.END_CID = cid1[1][i]
                        viewFindCTBtnParams.l = 1
                        tv.text = "T" + viewFindCTBtnParams.START_CID + "-" + "T" + viewFindCTBtnParams.END_CID
                        btn.text = (i + 1).toString()
//                viewModel.btnsParams.getValue().add(viewFindCTBtnParams);
                        viewModel.btnsParams1.value!!.add(viewFindCTBtnParams as FindCTBtnParams<View>)

                    }
                }
                if (viewModel.layout.value!!.size >= 2) {
                    val findCTBtnParams = viewModel.btnsParams1.value!![0]
                    val cid2 = CTNumberUtils.countCID(findCTBtnParams.END_CID - findCTBtnParams.START_CID + 1, viewModel.layout.value!![1])
                    for (i in 0 until binding.ll2.childCount) {
                        val btn = ((binding.ll2.getChildAt(i) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(1) as TextView
                        val tv = (binding.ll2.getChildAt(i) as LinearLayout).getChildAt(1) as TextView
                        val viewFindCTBtnParams = FindCTBtnParams<TextView>()
                        viewFindCTBtnParams.view = btn
                        viewFindCTBtnParams.tv = tv
                        val location = IntArray(2)
                        btn.getLocationOnScreen(location)
                        viewFindCTBtnParams.rawX = location[0].toFloat()
                        viewFindCTBtnParams.rawY = location[1].toFloat()
                        viewFindCTBtnParams.width = btn.width.toFloat()
                        viewFindCTBtnParams.height = btn.height.toFloat()
                        viewFindCTBtnParams.id = viewModel.btnsParams2.value!!.size + 1
                        viewFindCTBtnParams.START_CID = cid2[0][i]
                        viewFindCTBtnParams.END_CID = cid2[1][i]
                        viewFindCTBtnParams.l = 2
                        if (viewFindCTBtnParams.START_CID == viewFindCTBtnParams.END_CID)
                            tv.text = "T" + viewFindCTBtnParams.START_CID
                        else
                            tv.text = "T" + viewFindCTBtnParams.START_CID + "-" + "T" + viewFindCTBtnParams.END_CID
                        btn.text = (i + 1).toString()
//                viewModel.btnsParams.getValue().add(viewFindCTBtnParams);
                        viewModel.btnsParams2.value!!.add(viewFindCTBtnParams as FindCTBtnParams<View>)
                    }
                }
                if (layout.size >= 3) {
                    val findCTBtnParams = viewModel.btnsParams2.value!![0]
                    val cid3 = CTNumberUtils.countCID(findCTBtnParams.END_CID - findCTBtnParams.START_CID + 1, viewModel.layout.value!![1])
                    for (i in 0 until binding.ll3.childCount) {
                        val btn = ((binding.ll3.getChildAt(i) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(1) as TextView
                        val tv = (binding.ll3.getChildAt(i) as LinearLayout).getChildAt(1) as TextView

                        val location = IntArray(2)
                        //viewFindCTBtnParams.view?.tag=viewModel.getFIndT(idd).value?.getOrNull()?.data?.lightData
                        btn.getLocationOnScreen(location)
                        viewFindCTBtnParams.view = btn
                        viewFindCTBtnParams.tv = tv
                        viewFindCTBtnParams.rawX = location[0].toFloat()
                        viewFindCTBtnParams.rawY = location[1].toFloat()
                        viewFindCTBtnParams.width = btn.width.toFloat()
                        viewFindCTBtnParams.height = btn.height.toFloat()
                        viewFindCTBtnParams.id = viewModel.btnsParams3.value!!.size + 1
                        viewFindCTBtnParams.START_CID = cid3[0][i]
                        viewFindCTBtnParams.END_CID = cid3[1][i]
                        tv.text = "T${viewFindCTBtnParams.START_CID}"
                        btn.text = (i + 1).toString()
                        viewFindCTBtnParams.l = 3
                        viewModel.btnsParams3.value?.add(viewFindCTBtnParams as FindCTBtnParams<View>)
                    }
                }
            }
        }


    }
    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        chooseAdapter = RvIdentifiedTheCListAdapter(viewModel.chooseTheCList)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        val layoutManager2 = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        binding.rvThecurrentlyselectedTlist.layoutManager = layoutManager
        binding.rvThecurrentlyselectedTlist.adapter = chooseAdapter
        hasAuditAdapter = RvIdentifiedTheCListAdapter(viewModel.auditTCList)
        binding.rvAdjustedTlist.layoutManager = layoutManager2
        binding.rvAdjustedTlist.adapter = hasAuditAdapter

        binding.ll1.visibility = View.GONE
        binding.ll2.visibility = View.GONE
        binding.ll3.visibility = View.GONE

        binding.ll1.setOnTouchListener { view, motionEvent ->
            var rawX = motionEvent.rawX
            var rawY = motionEvent.rawY
            val X = view.x
            val Y = view.y
            val width = view.width
            val height = view.height
            if (rawX < X) {
                rawX = X
            } else if (rawX > X + width) {
                rawX = X + width
            }
            if (rawY < Y) {
                rawY = Y
            } else if (rawY > Y + height) {
                rawY = Y + width
            }
            val btns = viewModel.btnsParams1.value!!
            for (viewParams in btns!!) {
                if (rawX >= viewParams.rawX && rawX <= viewParams.rawX + viewParams.width) {
                    for (vp in btns) {
                        vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    }
                    if (viewModel.btnsParams2.value != null)
                        for (vp in viewModel.btnsParams2.value!!) {
                            vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                        }
                    if (viewModel.btnsParams3.value != null)
                        for (vp in viewModel.btnsParams3.value!!) {
                            vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                        }
                    viewParams.view?.setBackgroundResource(R.drawable.ct_ll_btn_selected_36dp)
                    if (viewParams.START_CID == viewParams.END_CID)
                        viewModel.currentlyDeterminedCBtn.value = viewParams
                    if (viewModel.btnsParams2.value != null) {
                        if(viewParams.END_CID - viewParams.START_CID + 1>0&& viewModel.btnsParams2.value!!.size>0)
                        {
                            val cids =
                                CTNumberUtils.countCID(
                                    viewParams.END_CID - viewParams.START_CID + 1,
                                    viewModel.btnsParams2.value!!.size)

                            for (i in viewModel.btnsParams2.value!!.indices) {
                                val viewFindCTBtnParams = viewModel.btnsParams2.value!![i]
                                viewFindCTBtnParams.START_CID =
                                    cids[0][i] + viewParams.START_CID - 1
                                viewFindCTBtnParams.END_CID =
                                    cids[1][i] + viewParams.START_CID - 1
                                if (viewFindCTBtnParams.START_CID == viewFindCTBtnParams.END_CID)
                                    viewFindCTBtnParams.tv?.text =
                                        "T" + viewFindCTBtnParams.START_CID
                                else
                                    viewFindCTBtnParams.tv?.text =
                                        "T" + viewFindCTBtnParams.START_CID + "-" + "T" + viewFindCTBtnParams.END_CID

                            }
                        }

                    }
                    break
                }
            }
            true
        }
        binding.ll2.setOnTouchListener { view, motionEvent ->
            var rawX = motionEvent.rawX
            var rawY = motionEvent.rawY
            val X = view.x
            val Y = view.y
            val width = view.width
            val height = view.height
            if (rawX < X) {
                rawX = X
            } else if (rawX > X + width) {
                rawX = X + width
            }
            if (rawY < Y) {
                rawY = Y
            } else if (rawY > Y + height) {
                rawY = Y + width
            }
            val btns = viewModel.btnsParams2.value!!
            for (viewParams in btns) {
                if (rawX >= viewParams.rawX && rawX <= viewParams.rawX + viewParams.width) {
                    for (vp in btns) {
                        vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    }
                    if (viewModel.btnsParams3.value != null) {
                        for (vp in viewModel.btnsParams3.value!!) {
                            vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                        }
                    }
                    viewParams.view?.setBackgroundResource(R.drawable.ct_ll_btn_selected_36dp)
                    if (viewParams.START_CID == viewParams.END_CID)
                        viewModel.currentlyDeterminedCBtn.value = viewParams
                    if (viewModel.btnsParams3.value != null) {
                        if(viewParams.END_CID - viewParams.START_CID + 1>0&& viewModel.btnsParams3.value!!.size>0)
                        {
                            val cids =
                                CTNumberUtils.countCID(
                                    viewParams.END_CID - viewParams.START_CID + 1,
                                    viewModel.btnsParams3.value!!.size
                                )
                            for (i in viewModel.btnsParams3.value!!.indices) {
                                val viewFindCTBtnParams = viewModel.btnsParams3.value!![i]
                                viewFindCTBtnParams.START_CID =
                                    cids[0][i] + viewParams.START_CID - 1
                                viewFindCTBtnParams.END_CID =
                                    cids[1][i] + viewParams.START_CID - 1
                                if (viewFindCTBtnParams.START_CID == viewFindCTBtnParams.END_CID)
                                    viewFindCTBtnParams.tv?.text =
                                        "T" + viewFindCTBtnParams.START_CID
                                else
                                    viewFindCTBtnParams.tv?.text =
                                        "T" + viewFindCTBtnParams.START_CID + "-" + "T" + viewFindCTBtnParams.END_CID

                            }
                        }
                        }

                    break
                }
            }
            true
        }
        binding.ll3.setOnTouchListener { view, motionEvent ->
            var rawX = motionEvent.rawX
            var rawY = motionEvent.rawY
            val X = view.x
            val Y = view.y
            val width = view.width
            val height = view.height
            if (rawX < X) {
                rawX = X
            } else if (rawX > X + width) {
                rawX = X + width
            }
            if (rawY < Y) {
                rawY = Y
            } else if (rawY > Y + height) {
                rawY = Y + width
            }
            val btns = viewModel.btnsParams3.value!!
            //viewModel.save(LightOffBody(btns.map { Light(it.id)  }))
           // viewModel.saveLightList(TPrecentageBody(btns.map { Light1(it.id,t_percentage)}))
            for (viewParams in btns) {
                if (rawX >= viewParams.rawX && rawX <= viewParams.rawX + viewParams.width) {
                    for (vp in btns) {
                        vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    }
                    viewParams.view?.setBackgroundResource(R.drawable.ct_ll_btn_selected_36dp)
                    if (viewParams.START_CID == viewParams.END_CID) {
                        viewModel.currentlyDeterminedCBtn.value = viewParams

                    }

                    break
                }
            }
            true
        }
    }

    override fun processFindT(result: LiveData<Result<FindT>>) {

        TODO("Not yet implemented")
    }

}
