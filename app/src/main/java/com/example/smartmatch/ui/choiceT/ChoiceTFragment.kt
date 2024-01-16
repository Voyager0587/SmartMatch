package com.example.smartmatch.ui.choiceT

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.chart.checkTAll
import com.example.smartmatch.databinding.FragmentChoiceTBinding
import com.example.smartmatch.logic.network.model.choicetdata
import com.example.smartmatch.ui.checkAll.CheckAllActivity
import com.example.smartmatch.ui.choiceT.ui.ChoiceTRvAdapter
import com.example.smartmatch.ui.choiceT.ui.HandFindTModel
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.findC.step_one.FindCStepOneViewModel


class ChoiceTFragment : BaseFragment<FragmentChoiceTBinding>(), ConstructionListener {

    private var listener: OnFragmentInteractionListener? = null
    private val viewModel: FindCStepOneViewModel by lazy {
        ViewModelProvider(requireActivity(),ViewModelProvider.NewInstanceFactory())[FindCStepOneViewModel::class.java]
    }
    val adapter = ChoiceTRvAdapter()
    var t_nums=-1
    var name=" "

    override fun onAttach(context: Context){
        super.onAttach(context)
        listener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }
    override fun FragmentChoiceTBinding.initBindingView() {
        binding.fragmentchocieviewModel= viewModel
        Log.e("TAG", requireActivity().toString())
        viewModel.name_area.toast()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name=arguments?.get("areaname").toString()
        // 在 onViewCreated 中观察 liveDataList
        viewModel.liveDataList.observe(viewLifecycleOwner, Observer { newList ->
            // 更新 RecyclerView 的数据
            adapter.submitList(newList)
        })


        // 初始化和设置 RecyclerView
        binding.ryChoiceT.layoutManager = LinearLayoutManager(requireContext())
        binding.ryChoiceT.adapter = adapter

        binding.handIv?.setOnClickListener {
            listener?.vm()

            // 注意：在这里不再手动提交列表，
            // 因为数据已经在 ViewModel 中被观察，会自动更新 RecyclerView
            Log.e("idd", viewModel.list.size.toString())
            if (viewModel.list.isNotEmpty()) {
                binding.fintTv.visibility = View.GONE
            }
        }
        binding.checkAllNextBt?.setOnClickListener{
//            listener?.vm2()
            val intent =Intent ()
            val receive=Bundle()
            if(receive!=null){
                t_nums=receive.getInt("tt",-1)
                Log.e("t_nums_chous",viewModel.t_num.toString())
            }

            intent.setClass(requireActivity(),CheckAllActivity::class.java)
            intent.putExtra("t_nums",viewModel.t_num)
            intent.putExtra("INT_CNUM",22)
            intent.putExtra("areaname",viewModel.name_area)
            startActivity(intent)

        }
    }

    interface OnFragmentInteractionListener {
        fun vm()
        fun vm1()
       // fun vm2()
    }


}


