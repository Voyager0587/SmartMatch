package com.example.smartmatch.ui.construction.lightcontrol

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.base.util.StatusUtil
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.FragmentLightControlBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton

/**
 * @className LightControlChooseFragment
 * @description 照明控制界面
 * @author Voyager
 * @date 2023/10/3 13:36
 */

class LightControlFragment : BaseFragment<FragmentLightControlBinding>(), ConstructionListener {

    private var area_id = -1
    private var area_index = -1
    private var mmnet_index = -1
    private val mViewModel: LightControlViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[LightControlViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun FragmentLightControlBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@LightControlFragment
        viewLifecycleOwner.safeLaunch {
            if (mViewModel.mmnetData == null)
                mViewModel.getMMNetData()
        }
        initListener()
        StatusUtil.initFragmentBar(this@LightControlFragment, false)

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

    override fun processFindT(result: LiveData<Result<FindT>>) {
        TODO("Not yet implemented")
    }

    override fun initListener() {
        super.initListener()
        binding.btNext.setOnClickListener {
            if (area_id != -1) {
                val fragment = LightControlScenarioFragment(mmnet_index, area_index, this)

                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(R.id.container_main, fragment)
                    hide(this@LightControlFragment)
                    setReorderingAllowed(true)
                    addToBackStack("name")
                    commit()
                }
            }
        }
        setVisibilityListener(binding.arrowButtonC, binding.containerC)
        setVisibilityListener(binding.arrowButtonArea, binding.containerArea)

    }


    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        super.initRecyclerList(mmnet_data)
        val context = requireContext()

        binding.containerArea.removeAllViews()
        binding.containerC.removeAllViews()

        for (i in mmnet_data.indices) {
            val name = mmnet_data[i].mmnet_name

            val net = createItemButton(context, name) {

                binding.searchControl.setText(name)
                binding.containerArea.removeAllViews()

                for (j in mmnet_data[i].areas.areas_data.indices) {
                    val areaName = mmnet_data[i].areas.areas_data[j].area.name
                    val area = createItemButton(context, areaName) {
                       // Toast.makeText(context, "Clicked on: $areaName", Toast.LENGTH_SHORT).show()
                        binding.searchArea.setText(areaName)
                        area_id = mmnet_data[i].areas.areas_data[j].area.id
                        area_index = j
                        mmnet_index = i
                    }
                    binding.containerArea.addView(area)
                }
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

    private fun setVisibilityListener(button: CompoundButton, container: View) {
        button.setOnClickListener {
            container.visibility = if (button.isChecked) View.VISIBLE else View.GONE
        }
    }



}