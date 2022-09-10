package dev.pinky.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.pinky.workoutlog.databinding.ActivitySignUpBinding
import dev.pinky.workoutlog.models.RegisterRequest
import dev.pinky.workoutlog.models.RegisterResponse
import dev.pinky.workoutlog.api.ApiClient
import dev.pinky.workoutlog.api.ApiInterface
import dev.pinky.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { registerResponse ->
            Toast.makeText(baseContext,registerResponse?.message,Toast.LENGTH_LONG).show()
                   // intent to login
            startActivity(Intent(this@SignUpActivity,LoginActivity::class.java))
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }

    fun validateSignup() {
        var firstname = binding.etFirstname.text.toString()
        var lastname = binding.etLastname.text.toString()
        var email = binding.etEmail1.text.toString()
        var phonenumber = binding.etPhonenumber.text.toString()
        var password = binding.etPassword1.text.toString()
        var confirm_password = binding.etConfirm.text.toString()

        var error = false
        if (firstname.isBlank()) {
            error = true
            binding.tilFirstname.error = "First name is required"
        }
        if (lastname.isBlank()) {
            error = true
            binding.tilLastname.error = "Last name is required"
        }
        if (email.isBlank()) {
            error = true
            binding.tilEmail1.error = "Email is required"
        }
        if (phonenumber.isBlank()) {
            error = true
            binding.tilPhonenumber.error = "Email is required"
        }
        if (password.isBlank()) {
            error = true
            binding.tilPassword1.error = "Password is required"
        }
        if (confirm_password.isBlank()) {
            error = true
            binding.tilConfirm.error = "confirm password required"
        }
        if (password != confirm_password) {
            error = true
            binding.tilConfirm.error = "wrong password"
        }
        if (!error){
            var registerRequest = RegisterRequest(firstname,lastname,email,phonenumber,password)
            userViewModel.register(registerRequest)
        }
    }
}