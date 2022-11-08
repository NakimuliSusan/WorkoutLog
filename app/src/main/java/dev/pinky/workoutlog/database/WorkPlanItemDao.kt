package dev.pinky.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.models.WorkoutPlanItem

@Dao
interface WorkPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem (workoutPlanItem: WorkoutPlanItem)

    @Query("SELECT * FROM WorkoutPlans WHERE workoutPlanId= :workoutPlanId AND day = :dayNumber")
    fun getTodayWorkoutPlanItem(workoutPlanId:String, dayNumber:Int): LiveData<WorkoutPlanItem>
}