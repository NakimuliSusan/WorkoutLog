package dev.pinky.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "WorkoutPlans")
data class WorkoutPlanItem(
    @PrimaryKey var workoutPlanItemId : String,
    var workoutPlanId : String,
    var day : String,
    var exerciseId: List<String>
)
