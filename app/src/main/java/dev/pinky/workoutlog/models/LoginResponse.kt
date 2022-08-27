package dev.pinky.workoutlog.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message : String,
    var accessToken : String,
    @SerializedName("user_id")var userId : String
)
