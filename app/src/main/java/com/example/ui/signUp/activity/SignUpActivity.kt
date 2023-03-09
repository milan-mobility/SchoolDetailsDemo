package com.example.ui.signUp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivitySignUpBinding
import com.example.ui.loginActivity.activity.LoginActivity
import com.example.ui.signUp.viewModel.CheckEmailViewModel
import com.example.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    lateinit var checkEmailViewModel: CheckEmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        checkEmailViewModel = ViewModelProvider(this)[CheckEmailViewModel::class.java]


        binding.registerViewModel = checkEmailViewModel

        binding.login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnSignUp.setOnClickListener {
            if (checkEmailViewModel.registerValidation()) {
                checkEmailViewModel.checkEmailApiCall()
            } else {
                binding.textInputLayoutEmail.helperText = checkEmailViewModel.blankEmail
                binding.textInputLayoutPassword.helperText = checkEmailViewModel.blankPassword
                binding.textInputLayoutConformPassword.helperText = checkEmailViewModel.blankConfirmPassword
            }
            checkEmailViewModel.blankEmail = ""
            checkEmailViewModel.blankPassword = ""
            checkEmailViewModel.blankConfirmPassword = ""
        }


        checkEmailViewModel.liveData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data!!.status) {
                        Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.data!!.message, Toast.LENGTH_SHORT).show()


                }
                Status.LOADING -> {


                }
            }
        }
    }
}