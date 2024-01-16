package com.example.smartmatch.ui.construction.scenedefine.scenedetails

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseActivity
import com.example.smartmatch.databinding.ActivitySceneDetailBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.network.model.ScenarioDetailResponse
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className SceneDetailActivity
 * @description 场景详情界面
 * @author Voyager
 * @date 2024/1/10 15:41
 */
class SceneDetailActivity : BaseActivity<ActivitySceneDetailBinding>(), ConstructionListener {


    public final val SCENARIO_ID = "scenarioId"
    private var currentFragment: Fragment? =null
    private val mViewModel: SceneDetailsViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[SceneDetailsViewModel::class.java]
    }

    override fun ActivitySceneDetailBinding.initBindingView() {
        binding.viewModel = mViewModel

        mViewModel.constructionListener = this@SceneDetailActivity
        val intent = intent
        val scenarioId = intent.getIntExtra(SCENARIO_ID, 0)
        viewModel?.scenarioId = scenarioId
        var fragment=SceneDetailFragment(scenarioId)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack("SceneDetailFragment").commit()
        currentFragment=fragment
    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        result.observe(this) { re ->
            re?.getOrNull()?.let { response ->
                val mmnetDataList = response.data.mmnet_data
                initRecyclerList(mmnetDataList)
            }
        }
    }

    override fun switchFragment(fragment: Fragment, tag: String) {
        super.switchFragment(fragment,tag)
        if (tag.equals("ChooseSceneFragment")||tag.equals("ChooseTFragment"))
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment).addToBackStack(tag).commit()
        else
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,
                    supportFragmentManager.findFragmentByTag(tag)!!
                ).addToBackStack(tag).commit()

    }

    override fun progressReturnData(result: LiveData<Result<ScenarioDetailResponse>>) {
        super.progressReturnData(result)
        result.observe(this){
//            re->
//            run {
//                mViewModel.scenarioDetailResponse = re.getOrNull()?.data
//            }
            //不需要了，在后面的Fragment中会解析数据
        }

    }
    //在fragment中也能调用切换的代码requireActivity
}