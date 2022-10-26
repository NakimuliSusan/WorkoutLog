package dev.pinky.workoutlog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.models.WorkoutPlanItem

@Dao
interface WorkPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem (workoutPlanItem: WorkoutPlanItem)
}