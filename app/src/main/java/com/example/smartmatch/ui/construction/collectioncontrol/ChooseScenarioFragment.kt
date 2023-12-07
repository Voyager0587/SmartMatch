package com.example.smartmatch.ui.construction.collectioncontrol

import android.content.Context
import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentChooseScenarioBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton

/**
 * @className ChooseScenarioFragment
 * @description 选择目标
 * @author Voyager
 * @date 2023/11/19 13:08
 */

class ChooseScenarioFragment(private val fragment: Fragment) :
    BaseFragment<FragmentChooseScenarioBinding>(), ConstructionListener {
    private var lastIndex = 0
    private var areaId = 0
    private val mViewModel by lazy {
        ViewModelProvider(
            fragment,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionControlViewModel::class.java]
    }

    override fun FragmentChooseScenarioBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@ChooseScenarioFragment
        mViewModel.getMMNetData()
        initListener()

    }
    override fun initListener() {
        setVisibilityListener(binding.arrowButtonArea, binding.containerArea)
        setVisibilityListener(binding.arrowButtonScenario, binding.containerScenario)

    }

    private fun setVisibilityListener(button: CompoundButton, container: View) {
        button.setOnClickListener {
            container.visibility = if (button.isChecked) View.VISIBLE else View.GONE
        }
    }

    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        val context = requireContext()

        binding.containerArea.removeAllViews()
        binding.containerScenario.removeAllViews()

        for (i in mmnet_data.indices) {

            for (j in mmnet_data[i].areas.areas_data.indices) {
                val areaName = mmnet_data[i].areas.areas_data[j].area.name
                val area = createItemButton(context, areaName) {
                    binding.searchArea.setText(areaName)
                    lastIndex = 0
                    binding.containerScenario.removeAllViews()
                    areaId = mmnet_data[i].areas.areas_data[j].area.id
                    for (k in mmnet_data[i].areas.areas_data[j].area.scenarios_data.indices) {
                        val sceneName =
                            mmnet_data[i].areas.areas_data[j].area.scenarios_data[k].name
                        val scene = createItemButton(context, sceneName) {
                            binding.searchScenario.setText(sceneName)
                            "Clicked on: $sceneName".toast()
                        }
                        binding.containerScenario.addView(scene)
                        lastIndex++
                    }

                }
                binding.containerArea.addView(area)
                lastIndex++
            }


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
        button.setOnClickListener(object : ItemButton.OnClickListener {
            override fun onTitleClick() {
                onClick()

            }

        })
        return button
    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        result.observe(this) { re ->
            re?.getOrNull()?.let { response ->
                val mmnetDataList = response.data.mmnet_data
                initRecyclerList(mmnetDataList)
            }
        }
    }
}