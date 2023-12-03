package com.example.smartmatch.ui.choiceT

import android.content.Intent
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivityChoiceTBinding
import com.example.smartmatch.ui.MainViewModel

class ChoiceTActivity :BaseActivity<ActivityChoiceTBinding>(){
    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    override fun ActivityChoiceTBinding.initBindingView() {
        binding.choicetviewModel=mViewModel
        binding.handIv?.setOnClickListener{
            val intent= Intent()
            intent.setClass(this@ChoiceTActivity,HandFindTActivity::class.java)
            startActivity(intent)
        }
        binding.smartIv?.setOnClickListener{
            val intent=Intent()
            //intent.setClass(this,LoadingActivity::class.java)
        }
        if(binding.ryChoiceT.size!=0){
            binding.fintTv!!.visibility
        }
    }
}