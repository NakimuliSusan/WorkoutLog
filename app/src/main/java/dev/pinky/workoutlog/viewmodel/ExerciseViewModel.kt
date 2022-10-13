package dev.pinky.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises
import dev.pinky.workoutlog.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel:ViewModel() {
    val exerciseRepository = ExerciseRepository()
    val  exerciseCategoryLiveData = MutableLiveData<List<ExerciseCategory>>()
    val  exerciseLiveData = MutableLiveData<List<Exercises>>()

    val errorLiveData = MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken: String) {
      viewModelScope.launch {
          val response = exerciseRepository.fetchExerciseCategories(accessToken)
          if (response.isSuccessful) {
              exerciseCategoryLiveData.postValue(response.body())
          }
          else {
              val errorMsg = response.errorBody()?.string()
              errorLiveData.postValue(errorMsg)
          }
      }
    }

    fun fetchExercises(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExercises(accessToken)
            if (response.isSuccessful) {
                exerciseLiveData.postValue(response.body())
            }
            else {
                val errorMsg = response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }
    }
}