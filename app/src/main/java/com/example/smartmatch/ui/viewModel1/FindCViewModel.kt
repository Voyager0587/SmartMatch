package com.example.smartmatch.ui.viewModel1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FindCViewModel :ViewModel(){
    val _jumpToArea = MutableSharedFlow<Int>()
    fun jumpToAreaDefine(random:Int=0){
        viewModelScope.launch {
            _jumpToArea.emit(random)
        }
    }

}