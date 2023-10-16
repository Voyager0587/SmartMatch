package com.example.smartmatch.ui.person.login

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.smartmatch.SmartApplication
import com.example.smartmatch.base.activity.BaseFragment
import com.example.smartmatch.databinding.FragmentLoginBinding
import com.example.smartmatch.logic.network.model.ResponseMessage
import com.example.smartmatch.ui.person.PersonListener

class LoginFragment : BaseFragment<FragmentLoginBinding>(), PersonListener {

    private val mViewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[LoginViewModel::class.java]
    }

    override fun FragmentLoginBinding.initBindingView() {
        binding.viewModel = mViewModel
        mViewModel.personListener = this@LoginFragment
        binding.btConfirm.setOnClickListener {
            mViewModel.login()
        }
        textInputAccount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mViewModel.username = s.toString().trim()
            }


        })
        textInputPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mViewModel.password = s.toString().trim()
            }

        })
    }

    @SuppressLint("CommitPrefEdits")
    override fun processResponse(result: LiveData<Result<ResponseMessage>>) {
        super.processResponse(result)
        result.observe(this) { re ->
            val message = re.getOrNull()
            SmartApplication.token= message?.token.toString()
            SmartApplication.sp.edit().putString("token",SmartApplication.token).apply()
        }
    }
}