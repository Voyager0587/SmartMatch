package com.example.smartmatch.ui.construction.collectioncontrol

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.base.util.safeLaunch
import com.example.smartmatch.databinding.FragmentCollectionControlBinding
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.model.helper.ScenarioHelper
import com.example.smartmatch.ui.construction.ConstructionListener
import com.example.smartmatch.ui.construction.adapter.CollectionScenarioAdapter

/**
 * @className ClusterControlFragment
 * @description 集合场景
 * @author Voyager
 * @date 2023/11/17 14:34
 */
class CollectionControlFragment : BaseFragment<FragmentCollectionControlBinding>(),
    ConstructionListener {
    private val mViewModel: CollectionControlViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionControlViewModel::class.java]

    }

    private var netId = -1
    lateinit var mAdapter: CollectionScenarioAdapter

    override fun FragmentCollectionControlBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@CollectionControlFragment
        viewLifecycleOwner.safeLaunch {
            mViewModel.getMMNetData()
        }
        initListener()
    }

    override fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {
        super.processMMNetData(result)
        result.observe(this) { re ->
            run {
                val response = re.getOrNull()
                response?.let {
                    val mmnetData = it.data.mmnet_data
                    initRecyclerList(mmnetData)
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.btnNext.setOnClickListener {
            var id= mAdapter.netId
            if (mAdapter.netId != -1) {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(R.id.container_main, NewCollectionSceneFragment(mAdapter.netId))
                    hide(this@CollectionControlFragment)
                    setReorderingAllowed(true)
                    addToBackStack("4343434")
                    commit()
                }
            }
        }



//        binding.etInput.setOnTouchListener { v, event ->
//
//            val DRAWABLE_RIGHT = 2
//
//            if (event.action == MotionEvent.ACTION_UP) {
//                if (event.rawX >= (binding.etInput.right - binding.etInput.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
//                    "hello".toast()
//                    return@setOnTouchListener true
//                }
//            }
//            return@setOnTouchListener false
//        }

        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        super.initRecyclerList(mmnet_data)
        mAdapter = CollectionScenarioAdapter()
        binding.netRecyclerview.adapter = mAdapter
        binding.netRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        val dataList = ArrayList<ScenarioHelper>()
        for (bean in mmnet_data)
            dataList.add(ScenarioHelper(bean.mmnet_name, bean.mmnet_id))
        mAdapter.setData(dataList)
    }


}