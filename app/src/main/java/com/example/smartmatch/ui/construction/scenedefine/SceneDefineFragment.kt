package com.example.smartmatch.ui.construction.scenedefine

import android.content.Context
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentSceneDefineBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.view.ItemButton
import com.kongzue.dialogx.dialogs.InputDialog

/**
 * @className SceneDefineFragment
 * @description 场景定义第一个界面
 * @author Voyager
 * @date 2023/9/30 14:03
 */
class SceneDefineFragment : BaseFragment<FragmentSceneDefineBinding>(), ConstructionListener {

    private var lastIndex = 0
    private val mViewModel: SceneDefineViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SceneDefineViewModel::class.java]
    }

    override fun FragmentSceneDefineBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@SceneDefineFragment
        mViewModel.getMMNetData()
        initListener()
    }

    override fun initListener() {
        setVisibilityListener(binding.arrowButtonC, binding.containerC)
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
                        lastIndex = 0
                        binding.searchControl.setText(name)
                        binding.containerScene.removeAllViews()

                        for (k in mmnet_data[i].areas.areas_data[j].area.scenarios_data.indices) {
                            val sceneName =
                                mmnet_data[i].areas.areas_data[j].area.scenarios_data[k].name
                            val scene = createItemButton(context, sceneName) {
                                Toast.makeText(
                                    context,
                                    "Clicked on: $sceneName",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            binding.containerScene.addView(scene)
                            lastIndex++
                        }

                        val newScene = createItemButton(context, "Create a new scene") {
                            InputDialog("Create a new scene", "Please enter a scene name", "Confirm", "Cancel", "")
                                .setCancelable(true)
                                .setOkButton { baseDialog, v, inputStr ->
                                    requireActivity().toast("Input content：$inputStr")
                                    addNewView(inputStr)
                                    false
                                }
                                .show()
                        }
                        binding.containerScene.addView(newScene)
                        val newScene2 = createItemButton(context, "Add a new scene based on an existing scene") {
                            InputDialog("Add a new scene based on an existing scene", "Please enter a scene name", "Confirm", "Cancel", "")
                                .setCancelable(true)
                                .setOkButton { baseDialog, v, inputStr ->
                                    requireActivity().toast("Input content：$inputStr")
                                    addNewView(inputStr)
                                    false
                                }
                                .show()
                        }
                        binding.containerScene.addView(newScene2)
                    }
                    binding.containerArea.addView(area)
                    lastIndex++
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
        toast("Choose $text")
        button.setOnClickListener { onClick() }
        button.setOnClickListener(object :ItemButton.OnClickListener{
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

    override fun processFindT(result: LiveData<Result<FindT>>) {

    }



}