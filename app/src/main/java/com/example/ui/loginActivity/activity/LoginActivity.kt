package com.example.ui.loginActivity.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivityLoginBinding
import com.example.ui.home.activity.MainActivity
import com.example.ui.loginActivity.viewModel.LoginViewModel
import com.example.ui.signUp.activity.SignUpActivity
import com.example.utils.Common.hideKeyboardFrom
import com.example.utils.MyDataStore
import com.example.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var dataStore: MyDataStore

    private lateinit var progressDialog: ProgressDialog

    companion object {
        var TAG = LoginActivity::class.simpleName
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginViewModel = viewModel

        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        progressDialog = ProgressDialog(this)

        binding.signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {

            if (viewModel.loginValidation()) {
                viewModel.loginApiCalling(this)
            } else {
                binding.textInputLayoutEmail.helperText = viewModel.blankEmail
                binding.textInputLayoutPassword.helperText = viewModel.blankPass
            }
            hideKeyboardFrom(this, view = null)

            viewModel.blankEmail = ""
            viewModel.blankPass = ""

        }

        viewModel.liveData.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data?.status!!) {

                        CoroutineScope(Dispatchers.IO).launch {

                            dataStore.insertUserID(it.data.data.id.toString())
                            dataStore.isUserLogin(true)

                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finishAffinity()
                        }
                        Log.d(TAG, "SUCCESS=>${it.data.message}")
                        Toast.makeText(this, "${it.data.message}", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this, "Not Success", Toast.LENGTH_SHORT).show()
                    }
                    progressDialog.dismiss()
                }

                Status.ERROR -> {
                    Log.d(TAG, "ERROR=>${it.data?.message}")
                    progressDialog.dismiss()
                }

                Status.LOADING -> {
                    progressDialog.setMessage(resources.getString(R.string.loading))
                    progressDialog.setCancelable(false)
                    progressDialog.show()

                }
            }
        }
    }

}
