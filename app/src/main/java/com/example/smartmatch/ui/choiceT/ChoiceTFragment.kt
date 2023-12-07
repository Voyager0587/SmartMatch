package com.example.smartmatch.ui.choiceT

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartmatch.R
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.chart.checkTAll
import com.example.smartmatch.databinding.FragmentChoiceTBinding
import com.example.smartmatch.ui.choiceT.ui.ChoiceTRvAdapter
import com.example.smartmatch.ui.choiceT.ui.HandFindTModel
import com.example.smartmatch.ui.construction.ConstructionListener


class ChoiceTFragment : BaseFragment<FragmentChoiceTBinding>(), ConstructionListener {

    private var listener: OnFragmentInteractionListener? = null
    private val viewModel: HandFindTModel by lazy {
        ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[HandFindTModel::class.java]
    }
    val adapter = ChoiceTRvAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun FragmentChoiceTBinding.initBindingView() {
        binding.fragmentchocieviewModel= viewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // 在 onViewCreated 中观察 liveDataList
        viewModel.liveDataList.observe(viewLifecycleOwner, Observer { newList ->
            // 更新 RecyclerView 的数据
            adapter.submitList(newList)
        })

        // 初始化和设置 RecyclerView
        binding.ryChoiceT.layoutManager = LinearLayoutManager(requireContext())
        binding.ryChoiceT.adapter = adapter

        binding.handIv?.setOnClickListener {
            listener?.vm()

            // 注意：在这里不再手动提交列表，
            // 因为数据已经在 ViewModel 中被观察，会自动更新 RecyclerView

            Log.e("idd", viewModel.list.size.toString())
            if (viewModel.list.isNotEmpty()) {
                binding.fintTv.visibility = View.GONE
            }
        }
    }

    interface OnFragmentInteractionListener {
        fun vm()
        fun vm1()
    }
}


