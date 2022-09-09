package dev.pinky.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.databinding.ActivityLoginBinding
import dev.pinky.workoutlog.models.LoginRequest
import dev.pinky.workoutlog.models.LoginResponse
import dev.pinky.workoutlog.models.RegisterResponse
import dev.pinky.workoutlog.retrofit.ApiClient
import dev.pinky.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
   lateinit var binding: ActivityLoginBinding
   lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)



        binding.btnLogin.setOnClickListener {
            validateLogin()
//      startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.tvSignup .setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
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
            makeLoginRequest(loginUser)
            binding.pbLogin.visibility = View.VISIBLE
        }
    }

     fun makeLoginRequest (loginRequest: LoginRequest) {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.loginUser(loginRequest)

        request.enqueue(object  : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin.visibility = View.GONE

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    saveLoginResponse(loginResponse!!)
                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                    // intent to login user
                startActivity(Intent(baseContext, HomeActivity::class.java))
                    finish()

                }
//                else if (){
//
//                }
                else{
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility = View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun saveLoginResponse(loginResponse: LoginResponse){
        val editor = sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()
    }

//    fun logout (loginResponse: LoginResponse) {
//        val editor = sharedPrefs.edit()
//        editor.putString("",loginResponse.accessToken)
//        editor.putString("",loginResponse.userId)
//        editor.putString("",loginResponse.profileId)
//        editor.apply()
//    }


}