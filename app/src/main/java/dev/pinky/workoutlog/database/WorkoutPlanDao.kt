package dev.pinky.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pinky.workoutlog.models.WorkoutPlan

@Dao
interface WorkoutPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlan (workoutPlan: WorkoutPlan)

    @Query(" SELECT * FROM WorkoutPlan WHERE userId =:userId")
    fun getWorkoutPlanByUserId (userId: String):LiveData<WorkoutPlan>
}