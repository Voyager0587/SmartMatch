package com.example.smartmatch.ui.construction.collectioncontrol

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentAddCollectionSceneBinding
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.ui.construction.ConstructionListener

/**
 * @className AddCollectionSceneFragment
 * @description 集合场景添加场景
 * @author Voyager
 * @date 2023/11/16 1:00
 */

class AddCollectionSceneFragment(private val fragment: Fragment) : BaseFragment<FragmentAddCollectionSceneBinding>(),
    ConstructionListener {
    private val mViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[CollectionControlViewModel::class.java]
    }

    override fun FragmentAddCollectionSceneBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.constructionListener = this@AddCollectionSceneFragment

    }

    override fun initListener() {
        super.initListener()
        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


    override fun initRecyclerList(mmnet_data: List<MmnetData>) {
        super.initRecyclerList(mmnet_data)
        mViewModel.mmnetData?.observe(this){
            re->
            run {
                var response = re.getOrNull()

            }
        }
    }
}