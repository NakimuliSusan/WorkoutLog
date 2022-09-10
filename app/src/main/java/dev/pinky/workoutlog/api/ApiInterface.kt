package dev.pinky.workoutlog.api

import dev.pinky.workoutlog.models.LoginRequest
import dev.pinky.workoutlog.models.LoginResponse
import dev.pinky.workoutlog.models.RegisterRequest
import dev.pinky.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body  loginRequest: LoginRequest): Response <LoginResponse>
}