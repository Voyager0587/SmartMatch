package com.example.smartmatch.ui.construction

import androidx.lifecycle.LiveData
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData

/**
 * @className: ConstructionListener
 * @author: Voyager
 * @description: 施工的监听器
 * @date:  2023/9/22 20:26
 * @version 1.0
 **/
interface ConstructionListener {

    /**
     * 获取当前MMNet的全部数据的回调处理方法
     */
    fun processMMNetData(result: LiveData<Result<MMNetResponse>>) {}

    fun initListener() {}

    /**
     * 获取当前MMNet的Area
     */
    fun getMMNetArea() {}

    /**
     * 初始化View的列表
     */
    fun initViewList(mmnet_data: List<MmnetData>){}

    /**
     * 添加新View
     */
    fun addNewView(name:String){}

}