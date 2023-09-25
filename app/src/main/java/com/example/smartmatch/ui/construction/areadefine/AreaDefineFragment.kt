package com.example.smartmatch.ui.construction.areadefine

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentAreaDefineBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.ui.construction.ConstructionListener



class AreaDefineFragment : BaseFragment<FragmentAreaDefineBinding>(),ConstructionListener {

    private val mViewModel:AreaDefineViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[AreaDefineViewModel::class.java]
    }
    override fun FragmentAreaDefineBinding.initBindingView() {
        binding.viewModel=mViewModel
        mViewModel.constructionListener=this@AreaDefineFragment
        chooseNet?.setOnClickListener {
            mViewModel.getMMNetData()
        }

        chooseArea?.setOnClickListener {

        }

    }

    override fun getMMNetData(result: LiveData<Result<MMNetResponse>>) {
        result.observe(this){re->
            val  response=re.getOrNull()//MMNet对象
            if (response != null) {
                saveMMNetData(response)
            }

        }
    }

    private fun saveMMNetData(response: MMNetResponse){
        var data=response.data
        var mmnetDataList=data.mmnet_data
        for(mmnetData in mmnetDataList){
            var area=mmnetData.areas
            var c=mmnetData.C
            
        }


    }
}