package dev.pinky.workoutlog.models

import com.google.gson.annotations.SerializedName

data class CreateProfilerequest (
    @SerializedName("user_id") var userId : String,
    var sex : String,
    @SerializedName("date_of_birth") var Date_Of_Birth : String,
    var weight : Double,
    var height : Int
)

