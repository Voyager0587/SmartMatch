package com.example.smartmatch.ui.construction

import androidx.lifecycle.LiveData
import com.example.smartmatch.logic.model.MMNetResponse

/**
 * @className: ConstructionListener
 * @author: Voyager
 * @description: 施工的监听器
 * @date:  2023/9/22 20:26
 * @version 1.0
 **/
interface ConstructionListener {

    /**
     * 获取当前MMNet的全部数据
     */
    fun getMMNetData(result: LiveData<Result<MMNetResponse>>) {}

    /**
     * 获取当前MMNet的Area
     */
    fun getMMNetArea() {}

}