package com.example.smartmatch.base.`interface`

import androidx.databinding.ViewDataBinding

/**
 * @author:SunShibo
 * @date:2022-09-26 22:25
 * @feature: viewBinding interface
 */
interface BaseBinding<VB : ViewDataBinding> {
    /**
     * VB的拓展函数
     *
     * 定义一个规范，要求实现该接口的类必须提供一个初始化绑定视图的方法。具体的实现逻辑需要在实现类中完成。
     */
    fun VB.initBindingView()
}
