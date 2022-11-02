package dev.pinky.workoutlog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.pinky.workoutlog.models.WorkoutLogRecord
import java.util.*

@Dao
interface WorkoutLogRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord)

    @Query("SELECT * FROM WorkoutLogRecord WHERE userId = :userId AND date >= :currentDate")
    fun getWorkoutLogsByuserId(userId: String, currentDate : String): LiveData<List<WorkoutLogRecord>>


}