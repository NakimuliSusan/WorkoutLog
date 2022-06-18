package dev.pinky.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import dev.pinky.workoutlog.databinding.ActivitySignUpBinding

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
        var password = binding.etPassword1.text.toString()
        var confirm_password = binding.etConfirm.text.toString()

        if (firstname.isBlank()) {
            binding.tilFirstname.error = "First name is required"
        }
        if (lastname.isBlank()) {
            binding.tilLastname.error = "Last name is required"
        }
        if (email.isBlank()) {
            binding.tilEmail1.error = "Email is required"
        }
        if (password.isBlank()) {
            binding.tilPassword1.error = "Password is required"
        }
        if (confirm_password.isBlank()) {
            binding.tilConfirm.error = "confirm password required"
        }
        if (password != confirm_password) {
            binding.tilConfirm.error = "wrong password"
        }
    }
}