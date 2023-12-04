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

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }
    val adapter=ChoiceTRvAdapter()

    override fun ActivityChoiceTBinding.initBindingView() {
        binding.choicetviewModel=mViewModel
        var data1=-1
        binding.handIv?.setOnClickListener{
//            val intent= Intent()
//            intent.setClass(this@ChoiceTActivity,HandFindTActivity::class.java)
//            startActivity(intent)
            data()



        }
        binding.smartIv?.setOnClickListener{
            val intent=Intent()
            //intent.setClass(this,LoadingActivity::class.java)
        }
        if(data().size!=null){
            binding.fintTv.visibility=View.GONE
        }

        val intent:Intent=getIntent()
         data1=intent.getIntExtra("LIST_T",data1)
        Log.e("idd",data1.toString())
        adapter.submitList(data())
        binding.ryChoiceT.layoutManager=LinearLayoutManager(this@ChoiceTActivity)
        binding.ryChoiceT.adapter=adapter
    }
    fun data(): List<checkTAll> {
        val list: ArrayList<checkTAll> = ArrayList()
        for (i in 1..5) {
            list.add(checkTAll(i))
            Log.e("iddddddd",list.size.toString())
        }
        return list
    }
}