package com.example.smartmatch.base.activity

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import com.example.base.util.StatusUtil
import com.example.smartmatch.base.`interface`.BaseBinding
import com.example.smartmatch.base.kxt.getViewBinding


abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), BaseBinding<VB> {
    protected lateinit var binding: VB
        private set

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusUtil.initFragmentBar(this, true)
    }


    /**
     *没有使用getViewBinding()函数的操作
     * override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View {
        binding = FragmentMyBinding.inflate(inflater, container, false)
        return binding.root
        }
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater, container)
        return binding.root
    }


    /**
     * super.onViewCreated(view, savedInstanceState)调用父类的onViewCreated()方法
     *
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initBindingView()
    }

    override fun onResume() {
        super.onResume()
        StatusUtil.checkNetWork(this.requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!::binding.isInitialized) return
        binding.unbind()
    }
}