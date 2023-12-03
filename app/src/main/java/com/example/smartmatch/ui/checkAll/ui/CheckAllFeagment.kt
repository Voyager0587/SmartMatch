package com.example.smartmatch.ui.checkAll.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.getViewBinding
import com.example.smartmatch.databinding.FragemntCheckallBinding
import com.example.smartmatch.databinding.FragmentFindCStepOneBinding
import com.example.smartmatch.ui.checkAll.CheckAllActivity
import com.example.smartmatch.ui.checkAll.ui.CheckAllViewModel
import com.example.smartmatch.ui.choiceT.ChoiceTActivity
import com.example.smartmatch.ui.construction.ConstructionListener



class CheckAllFragment : BaseFragment<FragemntCheckallBinding>(), ConstructionListener {

    companion object {
        fun newInstance() = CheckAllFragment()
    }
    private val viewModel: CheckAllViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[CheckAllViewModel::class.java]
    }
    val tv_cnum:TextView=binding.checkallAcnumTv
    val tv_areaname:TextView=binding.checkallAreanameTv
    val tv_tnum:TextView=binding.checkallAtnumTv
    override fun FragemntCheckallBinding.initBindingView() {
        binding.viewModel=viewModel

        initeView()
    }
    fun initeView(){
        var c_num=arguments?.get("xx")
        tv_cnum.text=c_num.toString()
        var areaname=arguments?.get("areaname")
        tv_areaname.text=areaname.toString()
        var t_num=arguments?.get("tnum")
        tv_tnum.text=t_num.toString()
    }


}