package com.example.smartmatch.ui.construction.areadefine

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentAreaDefineBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton


class AreaDefineFragment : BaseFragment<FragmentAreaDefineBinding>(), ConstructionListener {

    private val mViewModel: AreaDefineViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[AreaDefineViewModel::class.java]
    }

    override fun FragmentAreaDefineBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@AreaDefineFragment
        mViewModel.getMMNetData()
        arrowButtonC?.setOnClickListener {
            containerC?.visibility = View.GONE
        }
        arrowButtonArea?.setOnClickListener {
            containerC?.visibility = View.VISIBLE
        }

    }

    private fun init(mmnet_data: List<MmnetData>,) {

        for (i in mmnet_data.indices) {
            val net = ItemButton(requireContext(), null)
            net.text(mmnet_data[i].mmnet_name)
            net.setOnClickListener {
                binding.containerArea?.removeAllViews()
                for (j in mmnet_data[i].areas.areas_data.indices) {
                    val area = ItemButton(requireContext(), null)
                    area.text(mmnet_data[i].areas.areas_data[j].area.name)
                    area.setOnClickListener {
                        Toast.makeText(requireContext(), "Clicked on: ${"item$i"}", Toast.LENGTH_SHORT)
                            .show()
                    }
                    binding.containerArea?.addView(area)

                }
            }
            binding.containerC?.addView(net)




        }
    }

    override fun getMMNetData(result: LiveData<Result<MMNetResponse>>) {
        result.observe(this) { re ->
            val response = re.getOrNull()//MMNet对象
            if (response != null) {
                val data=response.data
                val mmnetDataList=data.mmnet_data
                init(mmnetDataList)
            }

        }
    }

    private fun saveMMNetData(response: MMNetResponse) {
        var data=response.data
        var mmnetDataList=data.mmnet_data
//        for(mmnetData in mmnetDataList){
//            var area=mmnetData.areas
//            var c=mmnetData.C
//
//        }


    }
}