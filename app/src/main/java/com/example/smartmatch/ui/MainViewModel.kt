package com.example.smartmatch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * @className: MainViewModel
 * @author: Voyager
 * @description: 主界面的ViewModel
 * @date:  2023/9/20 13:09
 * @version 1.0
 **/
class MainViewModel : ViewModel() {
    private val _jumpToFragment = MutableSharedFlow<Int>()
    val jumpToFragment = _jumpToFragment.asSharedFlow()

    fun jumpToAreaDefine(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }

    fun jumpToSceneDefine(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }
}