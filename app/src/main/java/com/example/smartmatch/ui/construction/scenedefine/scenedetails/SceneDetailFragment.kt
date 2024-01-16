package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.kxt.toast
import com.example.smartmatch.databinding.FragmentSceneDetailBinding
import com.example.smartmatch.logic.network.model.ScenarioDetailResponse
import com.example.smartmatch.ui.construction.ConstructionListener


class SceneDetailFragment(private var scenarioId:Int) : BaseFragment<FragmentSceneDetailBinding>(),
    ConstructionListener {
    private val mViewModel: SceneDetailsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SceneDetailsViewModel::class.java]
    }

    override fun FragmentSceneDetailBinding.initBindingView() {
        binding.viewModel = mViewModel
//        mViewModel.getScenarioDetail(19)


        btnBack.setOnClickListener {
            requireActivity().finish()
        }
        sceneLight.setOnClickListener {
            mViewModel.switchFragment(LightDetailFragment(),"LightDetailFragment")
        }
        subScene.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .hide(this@SceneDetailFragment).show(ChooseSceneFragment()).commit()
        }
    }

    override fun progressReturnData(result: LiveData<Result<ScenarioDetailResponse>>) {
        super.progressReturnData(result)
        result.observe(this) { re ->
            val response = re.getOrNull()
            if (response != null) {
                toast("数据："+ response.data?.light_data?.get(0)?.id.toString())

            }

        }

    }


}