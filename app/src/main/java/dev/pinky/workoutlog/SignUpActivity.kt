package dev.pinky.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var tilFirstname : TextInputLayout
    lateinit var tilLastname : TextInputLayout
    lateinit var tilEmail1    : TextInputLayout
    lateinit var tilPassword1 : TextInputLayout
    lateinit var tilConfirm : TextInputLayout
    lateinit var etFirstname : EditText
    lateinit var etLastname : EditText
    lateinit var etEmail1 : EditText
    lateinit var etPassword1 : EditText
    lateinit var etConfirm : EditText
    lateinit var btnSignup : Button
    lateinit var tvLogin  : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tilFirstname = findViewById(R.id.tilFirstname)
        tilLastname = findViewById(R.id.tilLastname)
        tilEmail1 = findViewById(R.id.tilEmail1)
        tilPassword1 = findViewById(R.id.tilPassword1)
        tilConfirm = findViewById(R.id.tilConfirm)
        etFirstname = findViewById(R.id.etFirstname)
        etLastname = findViewById(R.id.etLastname)
        etEmail1 = findViewById(R.id.etEmail1)
        etPassword1 = findViewById(R.id.etPassword1)
        etConfirm = findViewById(R.id.etConfirm)
        btnSignup = findViewById(R.id.btnSignup)
        tvLogin = findViewById(R.id.tvLogin)

        btnSignup.setOnClickListener {
            validateSignup()
        }
        tvLogin .setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }

    }

    fun validateSignup () {
        var firstname = etFirstname.text.toString()
        var lastname = etLastname.text.toString()
        var email = etEmail1.text.toString()
        var password = etPassword1.text.toString()
        var confirm_password = etConfirm.text.toString()

        if (firstname . isBlank()) {
            tilFirstname.error = "First name is required"
        }
        if (lastname . isBlank()) {
            tilLastname. error = "Last name is required"
        }
        if (email . isBlank()) {
            tilEmail1.error = "Email is required"
        }
        if (password. isBlank()) {
            tilPassword1.error = "Password is required"
        }
        if (confirm_password. isBlank()) {
            tilConfirm.error = "confirm password required"

        }
        if (password != confirm_password) {
            tilConfirm.error = "wrong password"

        }

    }
}