package dev.pinky.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.models.WorkoutPlanItem
import dev.pinky.workoutlog.repository.WorkoutPlanRespository
import kotlinx.coroutines.launch
import java.util.UUID

class WorkoutPlanViewModel:ViewModel() {
    val workoutPlanRespository = WorkoutPlanRespository()
    lateinit var  workoutPlanLivedata: LiveData<WorkoutPlan>
    var selectedExerciseIds = mutableListOf<String>()



    fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        viewModelScope.launch {
            workoutPlanRespository.saveWorkoutPlan(workoutPlan)
        }
    }

    fun getExistingWorkoutPlans(userId: String){
        workoutPlanLivedata = workoutPlanRespository.getWorkoutPlanByUserId(userId)
    }

    fun createWorkoutPlanItem (dayNumber: Int , workoutPlanId : String){
        val workoutPlanItem  = WorkoutPlanItem(
            workoutPlanItemId = UUID.randomUUID().toString(),
            workoutPlanId = workoutPlanId,
            day = dayNumber,
            exerciseId = selectedExerciseIds
        )
        viewModelScope.launch {
            workoutPlanRespository.saveWorkoutPlanItem(workoutPlanItem)
        }
    }
}