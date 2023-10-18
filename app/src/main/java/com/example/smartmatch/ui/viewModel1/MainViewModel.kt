package com.example.smartmatch.ui.viewModel1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){
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

    fun jumpToLightControl(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }

    /**
     * 个人界面
     */

    fun jumpToLogin(random: Int = 0) {
        viewModelScope.launch {
            _jumpToFragment.emit(random)
        }
    }

}