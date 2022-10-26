package dev.pinky.workoutlog.repository

import androidx.lifecycle.LiveData
import dev.pinky.workoutlog.WorkoutLog
import dev.pinky.workoutlog.database.WorkoutDb
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.models.WorkoutPlanItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanRespository {
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao = database.WorkoutPlanDao()
    val workPlanItemDao = database.WorkPlanItemDao()

   suspend fun saveWorkoutPlan (workoutPlan: WorkoutPlan){
        withContext(Dispatchers.IO){
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }
    suspend fun saveWorkoutPlanItem (workoutPlanItem: WorkoutPlanItem){
        withContext(Dispatchers.IO){
            workPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
        }
    }
    fun getWorkoutPlanByUserId (userId: String):LiveData<WorkoutPlan>{
        return  workoutPlanDao.getWorkoutPlanByUserId(userId)
    }
}