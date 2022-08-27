package dev.pinky.workoutlog.models

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name") var firstName : String,
    @SerializedName("last_name")var lastName :String,
    var email : String,
    @SerializedName("phone_number")var phonenumber : String,
    var password : String)