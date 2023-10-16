package com.example.smartmatch.ui.person.login

import androidx.lifecycle.ViewModel
import com.example.smartmatch.logic.Repository
import com.example.smartmatch.ui.person.PersonListener

/**
 * @className: LoginViewModel
 * @author: Voyager
 * @description: 登录ViewModel
 * @date:  2023/10/15 14:02
 * @version 1.0
 **/
class LoginViewModel : ViewModel() {
    private val repository = Repository
    internal var personListener: PersonListener? = null

    var username = ""
    var password = ""
    fun login() {
        val response  = repository.login(username, password)
        personListener?.processResponse(response)
    }
}