package com.example.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import com.example.schooldetailsdemo.R
import com.example.schooldetailsdemo.databinding.ActivitySplashScreenBinding
import com.example.ui.home.activity.MainActivity
import com.example.utils.MyDataStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    @Inject
    lateinit var splashViewModel: SplashViewModel

    @Inject
    lateinit var dataStore: MyDataStore

    companion object {
        var TAG = SplashScreenActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        /* splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]*/

//        splashViewModel.test()
        splashViewModel.abc()

        dataStore.isUserLogin.asLiveData().observe(this) {

            dataStore.getUserID.asLiveData().observe(this) { id ->
                    Log.d(TAG, "ID=>${id}}")
                    startActivity(
                        Intent(this, MainActivity::class.java))
                    finishAffinity()
                }


        }
    }
}