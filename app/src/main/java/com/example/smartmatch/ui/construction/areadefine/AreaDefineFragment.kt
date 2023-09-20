package com.example.smartmatch.ui.construction.areadefine

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentAreaDefineBinding


class AreaDefineFragment : BaseFragment<FragmentAreaDefineBinding>() {

    override fun FragmentAreaDefineBinding.initBindingView() {

        chooseNet.setOnClickListener {
            if(chooseNet.isChecked)
                netRecyclerView.visibility=VISIBLE
            else
                netRecyclerView.visibility= GONE
        }

        chooseArea.setOnClickListener {
            if(chooseArea.isChecked)
                areaRecyclerView.visibility= VISIBLE
            else
                areaRecyclerView.visibility= GONE
        }

    }

}