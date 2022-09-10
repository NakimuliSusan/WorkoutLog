package dev.pinky.workoutlog.repository

import dev.pinky.workoutlog.api.ApiClient
import dev.pinky.workoutlog.api.ApiInterface
import dev.pinky.workoutlog.models.LoginRequest
import dev.pinky.workoutlog.models.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun login (loginRequest: LoginRequest)
    = withContext(Dispatchers.IO){
        val response = apiClient.loginUser(loginRequest)
        return@withContext response
    }

    suspend fun register(registerRequest: RegisterRequest)
    = withContext(Dispatchers.IO) {
        val response = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}