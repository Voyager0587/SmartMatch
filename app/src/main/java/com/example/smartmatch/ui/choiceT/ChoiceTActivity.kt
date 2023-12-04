package com.example.smartmatch.ui.choiceT

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.chart.checkTAll
import com.example.smartmatch.databinding.ActivityChoiceTBinding
import com.example.smartmatch.ui.MainViewModel
import com.example.smartmatch.ui.choiceT.ui.ChoiceTRvAdapter

class ChoiceTActivity :BaseActivity<ActivityChoiceTBinding>(){
    val list: ArrayList<checkTAll> = ArrayList()
    var i:Int=-1
    var data1:ArrayList<Int> = ArrayList()
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }
    val adapter=ChoiceTRvAdapter()

    override fun ActivityChoiceTBinding.initBindingView() {
        binding.choicetviewModel=mViewModel

        binding.handIv?.setOnClickListener{
            val intent= Intent()
            intent.setClass(this@ChoiceTActivity,HandFindTActivity::class.java)
            startActivity(intent)

            i++
            // list.add(checkTAll(i))
            adapter.submitList(list)
            binding.ryChoiceT.layoutManager=LinearLayoutManager(this@ChoiceTActivity)
            binding.ryChoiceT.adapter=adapter
            binding.fintTv.visibility=View.GONE


        }
        binding.smartIv?.setOnClickListener{
            val intent=Intent()
            //intent.setClass(this,LoadingActivity::class.java)
        }


        val intent:Intent=getIntent()

        Log.e("idd",list.size.toString())

    }
    fun data(): List<checkTAll> {
        for (i in 1..5) {
            // list.add(checkTAll(i))
            Log.e("iddddddd",list.size.toString())
        }
        return list
    }
}