package com.example.smartmatch.ui.construction.clusterscene

import android.content.Context
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentChooseTargetBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.network.model.SceneCreationResponse
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton

/**
 * @className ChooseTargetFragment
 * @description 集群场景
 * @author Voyager
 * @date 2023/11/17 14:56
 */

class ChooseTargetFragment : BaseFragment<FragmentChooseTargetBinding>(), ConstructionListener {

    private var lastIndex = 0
    private var areaId = 0
    private val mViewModel: CollectionSceneViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionSceneViewModel::class.java]
    }

    override fun FragmentChooseTargetBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@ChooseTargetFragment
        initListener()
    }


    override fun initListener() {
        binding.btNext.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        setVisibilityListener(binding.arrowButtonC, binding.containerNet)
        setVisibilityListener(binding.arrowButtonArea, binding.containerArea)
        setVisibilityListener(binding.arrowButtonScene, binding.containerScene)

    }

    private fun setVisibilityListener(button: CompoundButton, container: View) {
        button.setOnClickListener {
            container.visibility = if (button.isChecked) View.VISIBLE else View.GONE
        }
    }


    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        val context = requireContext()

        binding.containerArea.removeAllViews()
        binding.containerNet.removeAllViews()

        for (i in mmnet_data.indices) {
            val name = mmnet_data[i].mmnet_name

            val net = createItemButton(context, name) {
                lastIndex = 0
                binding.searchControl.setText(name)
                binding.containerArea.removeAllViews()

                for (j in mmnet_data[i].areas.areas_data.indices) {
                    val areaName = mmnet_data[i].areas.areas_data[j].area.name
                    val area = createItemButton(context, areaName) {
                        lastIndex = 0
                        binding.searchControl.setText(name)
                        binding.containerScene.removeAllViews()
                        areaId = mmnet_data[i].areas.areas_data[j].area.id
                        for (scenario in mmnet_data[i].areas.areas_data[j].area.scenarios_data) {
                            val sceneName =
                                scenario.name
                            val scene = createItemButton(context, sceneName) {
                                "Clicked on: $sceneName".toast()
                                mViewModel.scenario = scenario
                            }
                            binding.containerScene.addView(scene)
                            lastIndex++
                        }

                    }
                    binding.containerArea.addView(area)
                    lastIndex++
                }
            }
            binding.containerNet.addView(net)
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

    override fun addNewView(name: String) {
        val scene = createItemButton(requireContext(), name) {
            requireContext().toast("点击场景：$name")
        }
        binding.containerScene.addView(scene, lastIndex)
        lastIndex++
    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        result.observe(this) { re ->
            re?.getOrNull()?.let { response ->
                val mmnetDataList = response.data.mmnet_data
                initRecyclerList(mmnetDataList)
            }
        }
    }


    override fun processTData(result: LiveData<Result<SceneCreationResponse>>) {
        super.processTData(result)
        result.observe(this) { re ->
            re?.getOrNull()?.let { response ->
                val lightDataList = response.data.light_data

            }
        }
    }


}