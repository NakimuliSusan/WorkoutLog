package dev.pinky.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinky.workoutlog.models.LoginRequest
import dev.pinky.workoutlog.models.LoginResponse
import dev.pinky.workoutlog.models.RegisterRequest
import dev.pinky.workoutlog.models.RegisterResponse
import dev.pinky.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
     val userRepository = UserRepository() // instance of user repository
    var loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginErrorLiveData = MutableLiveData<String?>() // nullable
    var registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val registerErrorLiveData = MutableLiveData<String?>()

    //livedata observes data
    fun loginUser (loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = userRepository.login(loginRequest)
            if (response.isSuccessful) {
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
               loginErrorLiveData.postValue(error)
            }
        }
    }
    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            val response = userRepository.register(registerRequest)
            if (response.isSuccessful) {
                registerResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }

}