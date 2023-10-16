package com.example.smartmatch.ui.person

import androidx.lifecycle.LiveData
import com.example.smartmatch.logic.network.model.ResponseMessage

/**
 * @className: PersonListenr
 * @author: Voyager
 * @description: TODO
 * @date:  2023/10/15 15:43
 * @version 1.0
 **/
interface PersonListener {
    /**
     * 处理通用的返回数据，确定是否操作成功
     */
    fun processResponse(result: LiveData<Result<ResponseMessage>>){}
}