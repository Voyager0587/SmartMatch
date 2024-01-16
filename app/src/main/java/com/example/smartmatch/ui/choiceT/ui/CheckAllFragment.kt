package com.example.smartmatch.ui.checkAll.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragemntCheckallBinding
import com.example.smartmatch.ui.checkAll.ui.CheckAllViewModel
import com.example.smartmatch.ui.choiceT.ChoiceTFragment
import com.example.smartmatch.ui.choiceT.ui.HandFindTModel
import com.example.smartmatch.ui.construction.ConstructionListener



class CheckAllFragment : BaseFragment<FragemntCheckallBinding>(), ConstructionListener {

    var tv_cnum:TextView?=null
    var tv_areaname:TextView?=null
    var tv_tnum:TextView?=null
    private var listener: ChoiceTFragment.OnFragmentInteractionListener? = null
    private val handfindTViewModel: CheckAllViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CheckAllViewModel::class.java]
    }


    override fun FragemntCheckallBinding.initBindingView() {
        binding.checkalviewmodel=handfindTViewModel
        tv_cnum=binding.checkallAcnumTv
        tv_areaname=binding.checkallAreanameTv
        tv_tnum=binding.checkallAtnumTv
        var c_num=arguments?.get("xx")
        tv_cnum!!.text=c_num.toString()
        var areaname=arguments?.get("areaname")
        tv_areaname!!.text=areaname.toString()
        var t_nums=arguments?.get("t_nums")
        tv_tnum!!.text=t_nums.toString()
        initeView()
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        listener = if (context is ChoiceTFragment.OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun initeView(){

    }


}