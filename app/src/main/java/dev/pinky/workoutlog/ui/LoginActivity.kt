package dev.pinky.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.databinding.ActivityLoginBinding
import dev.pinky.workoutlog.models.LoginRequest
import dev.pinky.workoutlog.models.LoginResponse
import dev.pinky.workoutlog.api.ApiClient
import dev.pinky.workoutlog.api.ApiInterface
import dev.pinky.workoutlog.util.Constants
import dev.pinky.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
   lateinit var binding: ActivityLoginBinding
   lateinit var sharedPrefs: SharedPreferences
   val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences(Constants.prefsFiles, MODE_PRIVATE)

// a coroutine is a unit of suspension compa

        binding.btnLogin.setOnClickListener {
            validateLogin()
//      startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.tvSignup .setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse ->
            saveLoginResponse(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            // intent to login user
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }


    fun validateLogin () {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false

        if (email.isBlank()){
            binding.tilEmail.error = getString(R.string.email_required)
            error = true
        }
        if (password.isBlank()){
            binding.tilPassword.error = getString(R.string.password_required)
            error = true
        }
        if (!error) {
           var loginUser = LoginRequest(email,password)
            userViewModel.loginUser(loginUser)
            binding.pbLogin.visibility = View.VISIBLE
        }
    }

    fun saveLoginResponse(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        val token = "Bearer  ${loginResponse.accessToken}"
        editor.putString(Constants.accessToken,token)
        editor.putString(Constants.userId,loginResponse.userId)
        editor.putString(Constants.profileId,loginResponse.profileId)
        editor.apply()
    }

}