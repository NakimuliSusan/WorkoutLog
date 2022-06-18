package dev.pinky.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import dev.pinky.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
   lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            validateLogin()
            startActivity(Intent(this,HomeActivity::class.java))
        }
        binding.tvSignup .setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
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
//            startActivity(Intent(this,HomeActivity::class.java))
//            finish()
        }
    }
}