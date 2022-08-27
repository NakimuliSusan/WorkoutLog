package dev.pinky.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.pinky.workoutlog.databinding.ActivitySignUpBinding
import dev.pinky.workoutlog.models.RegisterRequest
import dev.pinky.workoutlog.models.RegisterResponse
import dev.pinky.workoutlog.retrofit.ApiClient
import dev.pinky.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            validateSignup()
        }
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
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
            makeRegistrationRequest(registerRequest)
        }
    }

    fun makeRegistrationRequest(registerRequest: RegisterRequest){
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

       request.enqueue(object : Callback<RegisterResponse>{
           override fun onResponse(
               call: Call<RegisterResponse>,
               response: Response<RegisterResponse>
           ) {
               if (response.isSuccessful) {
                   var message = response.body()?.message
                   Toast.makeText(baseContext,message,Toast.LENGTH_LONG).show()
                   // intent to login

               }
               else{
                   val error = response.errorBody()?.string()
                   Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
               }
           }

           override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
               Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
           }
       })
    }
}