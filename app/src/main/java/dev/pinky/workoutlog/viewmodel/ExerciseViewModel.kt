package dev.pinky.workoutlog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises
import dev.pinky.workoutlog.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel:ViewModel() {
    val exerciseRepository = ExerciseRepository()
    lateinit var exerciseCategoryLiveData : LiveData<List<ExerciseCategory>>
    lateinit var  exerciseLiveData : LiveData<List<Exercises>>

    val errorLiveData = MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken: String) {
      viewModelScope.launch {
          val response = exerciseRepository.fetchExerciseCategories(accessToken)
          if (response.isSuccessful) {
              errorLiveData.postValue(response.body()?.toString())
          }
      }
    }

    fun fetchExercises(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExercises(accessToken)

        }
    }
    fun fetchDbCategories(){
        exerciseCategoryLiveData = exerciseRepository.getDbCategories()
    }

    fun fetchDbExercises(){
        exerciseLiveData = exerciseRepository.getDbExercises()
    }
}