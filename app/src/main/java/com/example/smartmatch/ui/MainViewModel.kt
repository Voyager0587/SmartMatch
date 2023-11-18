package com.example.smartmatch.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartmatch.logic.model.helper.CollectionScenarioHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    /**
     * 用于在视图模型中传递跳转到片段的操作
     */
    private val _jumpToFragment = MutableSharedFlow<Int>()
    val jumpToFragment = _jumpToFragment.asSharedFlow()

    /**
     * 复合场景助手的LiveData属性
     */
    var compositeScene = MutableLiveData<CollectionScenarioHelper>()

    /**
     * 获取复合场景助手的LiveData
     *
     * @return MutableLiveData对象
     */
    fun getCollectionScenarioHelper(): MutableLiveData<CollectionScenarioHelper> {
        return compositeScene
    }

    /**
     * 跳转到区域定义界面
     *
     * @param random 随机数（默认为0）
     */
    fun jumpToAreaDefine(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }

    /**
     * 跳转到场景定义界面
     *
     * @param random 随机数（默认为0）
     */
    fun jumpToSceneDefine(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }

    /**
     * 跳转到灯光控制界面
     *
     * @param random 随机数（默认为0）
     */
    fun jumpToLightControl(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }

    /**
     * 跳转到集合场景界面
     *
     * @param random 随机数（默认为0）
     */
    fun jumpToCollectionContrl(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }
    /**
     * 跳转到个人界面
     *
     * @param random 随机数（默认为0）
     */
    fun jumpToLogin(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }
}
