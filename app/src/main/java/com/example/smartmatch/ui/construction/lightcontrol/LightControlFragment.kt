package com.example.smartmatch.ui.construction.lightcontrol

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.base.util.StatusUtil
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentLightControlBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton

/**
 * @className LightControlFragment
 * @description 照明控制界面
 * @author Voyager 
 * @date 2023/10/3 13:36
 */

class LightControlFragment : BaseFragment<FragmentLightControlBinding>(),ConstructionListener {


    private val mViewModel:LightControlViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[LightControlViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun FragmentLightControlBinding.initBindingView() {
        binding.viewModel=mViewModel
        mViewModel.constructionListener=this@LightControlFragment
        mViewModel.getMMNetData()
        initListener()
        StatusUtil.initFragmentBar(this@LightControlFragment,false)

    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        super.processMMNetData(result)
        result.observe(this) { re ->
            val response=re.getOrNull()
            if(response!=null){
                val data=response.data
                val mmnetDataList=data.mmnet_data
                initRecyclerList(mmnetDataList)
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.btNext.setOnClickListener {
            requireContext().toast("下一步")
        }
        setVisibilityListener(binding.arrowButtonC, binding.containerC)
        setVisibilityListener(binding.arrowButtonArea, binding.containerArea)

    }



    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        super.initRecyclerList(mmnet_data)
    }

    private inline fun createItemButton(
        context: Context,
        text: String,
        crossinline onClick: () -> Unit
    ): ItemButton {
        val button = ItemButton(context, null)
        button.text(text)
        button.setOnClickListener { onClick() }
        return button
    }

    private fun setVisibilityListener(button: CompoundButton, container: View) {
        button.setOnClickListener {
            container.visibility=if(button.isChecked)View.VISIBLE else View.GONE
        }
    }

}