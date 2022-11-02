package dev.pinky.workoutlog.repository

import androidx.lifecycle.LiveData
import dev.pinky.workoutlog.WorkoutLog
import dev.pinky.workoutlog.database.WorkoutDb
import dev.pinky.workoutlog.database.WorkoutLogRecordDao
import dev.pinky.workoutlog.models.WorkoutLogRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class WorkoutLogRepository {
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao = database.WorkoutLogRecordDaoDao()

    suspend fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            workoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }

    fun getTodaysWorkoutLogRecords(userId:String, currentDate: String):
            LiveData<List<WorkoutLogRecord>>{
        return  workoutLogRecordDao.getWorkoutLogsByuserId(userId, currentDate)
    }
}