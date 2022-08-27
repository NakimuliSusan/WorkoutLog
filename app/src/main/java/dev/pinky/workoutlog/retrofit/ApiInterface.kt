package dev.pinky.workoutlog.retrofit

import dev.pinky.workoutlog.models.LoginRequest
import dev.pinky.workoutlog.models.LoginResponse
import dev.pinky.workoutlog.models.RegisterRequest
import dev.pinky.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")

    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body  loginRequest: LoginRequest): Call <LoginResponse>
}