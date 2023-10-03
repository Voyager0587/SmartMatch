package com.example.smartmatch.ui.construction.areadefine

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.base.util.StatusUtil
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentAreaDefineBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton
import com.kongzue.dialogx.dialogs.InputDialog

/**
 * @className AreaDefineFragment
 * @description 场景定义
 * @author Voyager
 * @date 2023/10/2 19:09
 */

class AreaDefineFragment : BaseFragment<FragmentAreaDefineBinding>(), ConstructionListener {
    /**
     * area的view数组的最后元素的index，便于删除view
     */
    private var lastIndex = 0

    private val mViewModel: AreaDefineViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[AreaDefineViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun FragmentAreaDefineBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@AreaDefineFragment
        mViewModel.getMMNetData()
        initListener()
        StatusUtil.initFragmentBar(this@AreaDefineFragment, false)
    }

    override fun initListener() {
        super.initListener()
        setVisibilityListener(binding.arrowButtonC, binding.containerC)
        setVisibilityListener(binding.arrowButtonArea, binding.containerArea)
    }

    private fun setVisibilityListener(button: CompoundButton, container: View) {
        button.setOnClickListener {
            container.visibility = if (button.isChecked) View.VISIBLE else View.GONE
        }
    }

    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        super.initRecyclerList(mmnet_data)
        val context = requireContext()

        binding.containerArea.removeAllViews()
        binding.containerC.removeAllViews()

        for (i in mmnet_data.indices) {
            val name = mmnet_data[i].mmnet_name

            val net = createItemButton(context, name) {
                lastIndex = 0
                binding.searchControl.setText(name)
                binding.containerArea.removeAllViews()

                for (j in mmnet_data[i].areas.areas_data.indices) {
                    val areaName = mmnet_data[i].areas.areas_data[j].area.name
                    val area = createItemButton(context, areaName) {
                        Toast.makeText(context, "Clicked on: $areaName", Toast.LENGTH_SHORT).show()
                    }
                    binding.containerArea.addView(area)
                    lastIndex++
                }

                val newArea = createItemButton(context, "新建区域") {
                    InputDialog("新建区域", "请输入区域名称", "确定", "取消", "正在输入的文字")
                        .setCancelable(true)
                        .setOkButton { baseDialog, v, inputStr ->
                            requireActivity().toast("输入的内容：$inputStr")
                            addNewView(inputStr)
                            mViewModel.createNewArea(100, inputStr)
                            false
                        }
                        .show()
                }
                binding.containerArea.addView(newArea)
            }
            binding.containerC.addView(net)
        }
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

    override fun addNewView(name: String) {
        super.addNewView(name)
        val area = ItemButton(requireContext(), null)
        area.text(name)
        area.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked on", Toast.LENGTH_SHORT)
                .show()
        }
        binding.containerArea.addView(area, lastIndex)
        lastIndex++
    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        super.processMMNetData(result)
        result.observe(this) { re ->
            val response = re.getOrNull()
            if (response != null) {
                val data = response.data
                val mmnetDataList = data.mmnet_data
                initRecyclerList(mmnetDataList)
            }

        }
    }


}