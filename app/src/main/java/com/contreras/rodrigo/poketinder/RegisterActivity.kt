package com.contreras.rodrigo.poketinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.contreras.rodrigo.poketinder.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = RegisterViewModel(this)
        observeValues()

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtPassword2.text.toString()

            registerViewModel.registerUser(email, password, confirmPassword)
        }

        binding.btnBackClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeValues() {
        registerViewModel.emailFormatError.observe(this) {
            Toast.makeText(this, "Formato de correo inválido", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordLengthError.observe(this) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordMatchError.observe(this) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }

        registerViewModel.registrationSuccess.observe(this) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
