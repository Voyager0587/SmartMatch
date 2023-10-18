package com.example.smartmatch.ui.choiceT

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
//      binding.handIv?.setOnClickListener{
//          val intent= Intent()
//          intent.setClass(this,HandFindT::class.java)
//          startActivity(intent)
//      }
//        binding.smartIv?.setOnClickListener{
//            val intent=Intent()
//            intent.setClass(this,LoadingActivity::class.java)
//        }
    }
}