package com.example.smartmatch.ui.construction

import androidx.lifecycle.LiveData
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.logic.network.model.ResponseMessage
import com.example.smartmatch.logic.network.model.SceneCreationResponse

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

    fun processTData(result: LiveData<Result<SceneCreationResponse>>) {}

    fun processSceneCloseResponse(result:LiveData<Result<ResponseMessage>>){}

    /**
     * 处理通用的返回数据，确定是否操作成功
     */
    fun processResponse(result:LiveData<Result<ResponseMessage>>){}
    fun processFindT(result: LiveData<Result<FindT>>){}

    fun initListener() {}

    /**
     * 获取当前MMNet的Area
     */
    fun getMMNetArea() {}

    /**
     * 初始化View的列表
     */
    fun initRecyclerList(mmnet_data: List<MmnetData>){}

    /**
     * 添加新View
     */
    fun addNewView(name:String){}

}