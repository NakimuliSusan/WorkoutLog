package dev.pinky.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.repository.WorkoutPlanRespository
import kotlinx.coroutines.launch

class WorkoutPlanViewModel:ViewModel() {
    val workoutPlanRespository = WorkoutPlanRespository()
    lateinit var  workoutPlanLivedata: LiveData<WorkoutPlan>

    fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        viewModelScope.launch {
            workoutPlanRespository.saveWorkoutPlan(workoutPlan)
        }
    }

    fun getExistingWorkoutPlans(userId: String){
        workoutPlanLivedata = workoutPlanRespository.getWorkoutPlanByUserId(userId)
    }
}