package com.contreras.rodrigo.poketinder

import android.content.SharedPreferences
import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(private val context: Context) : ViewModel() {

    val emailFormatError = MutableLiveData<Boolean>()
    val passwordLengthError = MutableLiveData<Boolean>()
    val passwordMatchError = MutableLiveData<Boolean>()
    val registrationSuccess = MutableLiveData<Boolean>()

    // Crear la instancia del repositorio dentro del ViewModel
    private var sharedPreferencesRepository: SharedPreferencesRepository =
        SharedPreferencesRepository().also {
            it.setSharedPreference(context)
        }

    fun registerUser(email: String, password: String, confirmPassword: String) {
        // Verificar el formato del correo
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailFormatError.value = true
            return
        }

        // Verificar la longitud de la contraseña
        if (password.length < 8) {
            passwordLengthError.value = true
            return
        }

        // Verificar si las contraseñas coinciden
        if (password != confirmPassword) {
            passwordMatchError.value = true
            return
        }

        sharedPreferencesRepository.saveUserEmail(email)
        sharedPreferencesRepository.saveUserPassword(password)

        registrationSuccess.value = true
    }
}