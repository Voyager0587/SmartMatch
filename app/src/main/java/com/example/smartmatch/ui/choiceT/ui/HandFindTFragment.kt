package com.example.smartmatch.ui.choiceT.ui
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.eazylight.ui.find_C.step_one.adapter.RvIdentifiedTheCListAdapter
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.util.CTNumberUtils
import com.example.smartmatch.chart.FindCTBtnParams
import com.example.smartmatch.chart.checkTAll
import com.example.smartmatch.databinding.FragmentHandFindTBinding
import com.example.smartmatch.logic.model.helper.choiceT
import com.example.smartmatch.ui.checkAll.CheckAllActivity
import com.example.smartmatch.ui.choiceT.ChoiceTFragment
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.findC.step_one.FindCStepOneViewModel
import java.util.ArrayList


class HandFindTFragment : BaseFragment<FragmentHandFindTBinding>(), ConstructionListener {
    private var isFragmentAttached = false
    private lateinit var adapter: RvIdentifiedTheCListAdapter
    val cdc=activity?.findViewById<TextView>(R.id.currently_determined_C_btn)
    private var listener: ChoiceTFragment.OnFragmentInteractionListener? = null
    var i:Int=-1
    var t:Int=0
    lateinit var trueC:List<choiceT>
    private val viewModel: FindCStepOneViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FindCStepOneViewModel::class.java)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is ChoiceTFragment.OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }

        // 设置标志表示Fragment已经附加到Activity
        isFragmentAttached = true
    }
    override fun onDetach() {
        super.onDetach()

        // 重置标志表示Fragment已经分离
        isFragmentAttached = false
    }
    override fun FragmentHandFindTBinding.initBindingView() {
        binding.vm=viewModel
        binding.click=Click()
        viewModel?.constructionListener=this@HandFindTFragment

        initeView()
    }


    @SuppressLint("SuspiciousIndentation")
    fun initeView() {
        binding.button4.setOnClickListener {
            var newV = FindCTBtnParams<View>()
            var value = viewModel.currentlyDeterminedCBtn.value
                viewModel.list.add(checkTAll(value!!.START_CID))
            Toast.makeText(context, "成功加入：${value.START_CID}灯", Toast.LENGTH_SHORT).show()
            viewModel.t_num++
            Log.e("idxxx", t.toString())
        }
        binding.button3.setOnClickListener{
                        viewModel.CNumber.observe(requireActivity()) { integer ->
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
                // 更新viewModel.layout
                viewModel.layout.value = layout
            }
            viewModel.layout.observe(requireActivity()) { layout ->
                // 清除旧布局
                binding.ll1.removeAllViews()
                binding.ll2.removeAllViews()
                binding.ll3.removeAllViews()

                // 改变布局显示
                if (layout == null) {
                    return@observe
                }
                // 其他布局代码...
            }

            val bundle = Bundle()
            Log.e("idxx", t.toString())
            bundle.putInt("tt", t)
            val existingFragment = requireActivity().supportFragmentManager.findFragmentByTag(ChoiceTFragment::class.java.simpleName)
            if (existingFragment != null && existingFragment is ChoiceTFragment) {
                // 如果 ChoiceTFragment 已存在，则更新参数
                existingFragment.arguments = bundle
            } else {
                // 如果 ChoiceTFragment 不存在，则创建一个新的实例并传递参数
                val recive = ChoiceTFragment()
                recive.arguments = bundle
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.findCFrameLayout, recive)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            listener?.vm1()
        }
        viewModel.updateList(viewModel.list)
    }




    inner class Click {
        fun confirm(view: View) {
            var value = viewModel.currentlyDeterminedCBtn.value
            if (value != null) {
                val listValue = viewModel.identifiedTheCList
                for (vp in listValue) {
                    if (vp.START_CID == value.START_CID) return
                }
                //必须进行深拷贝！！！
                var newV = FindCTBtnParams<View>()
                newV.START_CID = value.START_CID
                listValue.add(newV)
                adapter.notifyItemChanged(listValue.size)
//                adapter.notifyDataSetChanged();
            }
        }

        fun start(view: View) {
            viewModel.identifiedTheCList.clear()
            adapter.notifyDataSetChanged()
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
            //改变布局显示
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
                    textView.text = "C-C"
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
                    textView.text = "C-C"
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
                    textView.text = "C-C"
                    textView.setTextColor(Color.BLACK)
                    textView.gravity = Gravity.CENTER
                    linearLayout.addView(btnLL)
                    linearLayout.addView(textView)
                    binding.ll3.addView(linearLayout, lp)
                }
            }
           // initViewParams()
        }

    }

    override fun initDataAfterView() {
        viewModel.CNumber.value = 127
    }

    fun initViewParams() {
        if (!isAdded()) {
            return
        }
        if (binding.ll1 == null || binding.ll2 == null || binding.ll3 == null) {
            return
        }
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
                    tv.text = "C" + viewFindCTBtnParams.START_CID + "-" + "C" + viewFindCTBtnParams.END_CID
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
                        tv.text = "C" + viewFindCTBtnParams.START_CID
                    else
                        tv.text = "C" + viewFindCTBtnParams.START_CID + "-" + "C" + viewFindCTBtnParams.END_CID
                    btn.text = (i + 1).toString()
//                viewModel.btnsParams.getValue().add(viewFindCTBtnParams);
                    viewModel.btnsParams2.value!!.add(viewFindCTBtnParams as FindCTBtnParams<View>)
                }
            }
            if (viewModel.layout.value!!.size >= 3) {
                val findCTBtnParams = viewModel.btnsParams2.value!![0]
                val cid3 = CTNumberUtils.countCID(findCTBtnParams.END_CID - findCTBtnParams.START_CID + 1, viewModel.layout.value!![1])
                for (i in 0 until binding.ll3.childCount) {
                    val btn = ((binding.ll3.getChildAt(i) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(1) as TextView
                    val tv = (binding.ll3.getChildAt(i) as LinearLayout).getChildAt(1) as TextView
                    val viewFindCTBtnParams = FindCTBtnParams<TextView>()
                    viewFindCTBtnParams.view = btn
                    viewFindCTBtnParams.tv = tv
                    val location = IntArray(2)
                    btn.getLocationOnScreen(location)
                    viewFindCTBtnParams.rawX = location[0].toFloat()
                    viewFindCTBtnParams.rawY = location[1].toFloat()
                    viewFindCTBtnParams.width = btn.width.toFloat()
                    viewFindCTBtnParams.height = btn.height.toFloat()
                    viewFindCTBtnParams.id = viewModel.btnsParams3.value!!.size + 1
                    viewFindCTBtnParams.START_CID = cid3[0][i]
                    viewFindCTBtnParams.END_CID = cid3[1][i]
                    tv.text = "C" + viewFindCTBtnParams.START_CID
                    cdc?.text="C" + viewFindCTBtnParams.START_CID
                    btn.text = (i + 1).toString()
                    viewFindCTBtnParams.l = 3
                    viewModel.btnsParams3.value!!.add(viewFindCTBtnParams as FindCTBtnParams<View>)
                }
            }



    }

    @SuppressLint("ClickableViewAccessibility", "SuspiciousIndentation")
    override fun initView() {
        adapter = RvIdentifiedTheCListAdapter(viewModel.identifiedTheCList)
        val layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)

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
            val btns = viewModel.btnsParams1.value
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
                    //进行选中
                    viewParams.view?.setBackgroundResource(R.drawable.ct_ll_btn_selected_36dp)

                    if (viewParams.START_CID == viewParams.END_CID)
                        viewModel.currentlyDeterminedCBtn.value = viewParams
                    if (viewModel.btnsParams2.value != null) {
                        val cids =
                            CTNumberUtils.countCID(viewParams.END_CID - viewParams.START_CID + 1, viewModel.btnsParams2.value!!.size)
                        for (i in viewModel.btnsParams2.value!!.indices) {
                            val viewFindCTBtnParams = viewModel.btnsParams2.value!![i]
                            viewFindCTBtnParams.START_CID = cids[0][i] + viewParams.START_CID - 1
                            viewFindCTBtnParams.END_CID = cids[1][i] + viewParams.START_CID - 1
                            if (viewFindCTBtnParams.START_CID == viewFindCTBtnParams.END_CID)
                                viewFindCTBtnParams.tv?.text =
                                    "C" + viewFindCTBtnParams.START_CID
                            else
                                viewFindCTBtnParams.tv?.text =
                                    "C" + viewFindCTBtnParams.START_CID + "-" + "C" + viewFindCTBtnParams.END_CID

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
            val btns = viewModel.btnsParams2.value
            for (viewParams in btns!!) {
                if (rawX >= viewParams.rawX && rawX <= viewParams.rawX + viewParams.width) {
                    for (vp in btns) {
                        vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    }
                    if (viewModel.btnsParams3.value != null) {
                        for (vp in viewModel.btnsParams3.value!!) {
                            vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                        }
                    }
                    //进行选中
                    viewParams.view?.setBackgroundResource(R.drawable.ct_ll_btn_selected_36dp)
                    if (viewParams.START_CID == viewParams.END_CID)
                        viewModel.currentlyDeterminedCBtn.value = viewParams
                    if (viewModel.btnsParams3.value != null) {
                        val cids =
                            CTNumberUtils.countCID(viewParams.END_CID - viewParams.START_CID + 1, viewModel.btnsParams3.value!!.size)
                        for (i in viewModel.btnsParams3.value!!.indices) {
                            val viewFindCTBtnParams = viewModel.btnsParams3.value!![i]
                            viewFindCTBtnParams.START_CID = cids[0][i] + viewParams.START_CID - 1
                            viewFindCTBtnParams.END_CID = cids[1][i] + viewParams.START_CID - 1
                            if (viewFindCTBtnParams.START_CID == viewFindCTBtnParams.END_CID)
                                viewFindCTBtnParams.tv?.text =
                                    "C" + viewFindCTBtnParams.START_CID
                            else
                                viewFindCTBtnParams.tv?.text =
                                    "C" + viewFindCTBtnParams.START_CID + "-" + "C" + viewFindCTBtnParams.END_CID

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
            val btns = viewModel.btnsParams3.value
            for (viewParams in btns!!) {
                if (rawX >= viewParams.rawX && rawX <= viewParams.rawX + viewParams.width) {
                    for (vp in btns) {//清空选中
                        vp.view?.setBackgroundResource(R.drawable.ct_ll_btn_36dp)
                    }
                    //进行选中
                    viewParams.view?.setBackgroundResource(R.drawable.ct_ll_btn_selected_36dp)
                    if (viewParams.START_CID == viewParams.END_CID)
                        viewModel.currentlyDeterminedCBtn.value = viewParams


                    break
                }
            }
            true
        }
    }


}
