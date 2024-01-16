package com.example.smartmatch.ui.construction

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.smartmatch.logic.model.MMNetResponse
import com.example.smartmatch.logic.model.MmnetData
import com.example.smartmatch.logic.model.helper.FindT
import com.example.smartmatch.logic.network.model.CanAddScenesResponse
import com.example.smartmatch.logic.network.model.CollectionScenariosResponse
import com.example.smartmatch.logic.network.model.FindCData
import com.example.smartmatch.logic.network.model.FindTData
import com.example.smartmatch.logic.network.model.ResponseMessage
import com.example.smartmatch.logic.network.model.ScenarioDetailResponse
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

    /**
     * 处理集合场景响应的函数
     * @param result 活跃的集合场景响应结果的LiveData
     */
    fun processCollectionScenariosResponse(result: LiveData<Result<CollectionScenariosResponse>>){}

    /**
     * 处理T数据的回调处理方法
     * @param result T数据结果的LiveData
     */
    fun processTData(result: LiveData<Result<SceneCreationResponse>>){}

    /**
     * 处理场景关闭响应的回调处理方法
     * @param result 场景关闭响应结果的LiveData
     */
    fun processSceneCloseResponse(result:LiveData<Result<ResponseMessage>>){}

    /**
     * 处理通用的返回数据，确定是否操作成功
     */
    fun processResponse(result:LiveData<Result<ResponseMessage>>){}

    /**
     * 处理FindT数据的回调处理方法
     * @param result FindT数据结果的LiveData
     */
    fun processFindT(result: LiveData<Result<FindT>>){}

    /**
     * 初始化监听器
     */
    fun initListener() {}

    /**
     * 获取当前MMNet的Area
     */
    fun getMMNetArea() {}

    /**
     * 初始化View的列表
     * @param mmnet_data MMNet数据列表
     */
    fun initRecyclerList(mmnet_data: List<MmnetData>){}

    /**
     * 添加新View
     * @param name View的名称
     */
    fun addNewView(name:String){}
    /**
     * FindC的东西
     */
    fun findCData(result: LiveData<Result<FindCData>>){}
    /**
     * 找T界面获取数据
     */
    fun findTResponse(result: LiveData<Result<FindTData>>){}

    fun progressReturnData(result: LiveData<Result<ScenarioDetailResponse>>){}

    fun switchFragment(fragment: Fragment, tag: String){}

    fun processAddSceneResponse(result: LiveData<Result<CanAddScenesResponse>>){}
}