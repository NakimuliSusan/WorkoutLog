package dev.pinky.workoutlog.repository

import dev.pinky.workoutlog.WorkoutLog
import dev.pinky.workoutlog.api.ApiClient
import dev.pinky.workoutlog.api.ApiInterface
import dev.pinky.workoutlog.database.WorkoutDb
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val exerciseCategoryDao = database.exerciseCategoryDao()
    val exerciseDao = database.exerciseDao()

    suspend fun  fetchExerciseCategories(accessToken: String):Response<List<ExerciseCategory>>{
      return withContext(Dispatchers.IO) {
         var response = apiClient.fetchExerciseCategories(accessToken)
          if (response.isSuccessful){
              var categories = response.body()
              categories?.forEach{ category->
                  exerciseCategoryDao.insertExerciseCategory(category)
              }
          }
          response
      }
    }
    suspend fun fetchExercises(accessToken: String):Response<List<Exercises>> {
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful){
                var exercises = response.body()
                exercises?.forEach{ exercise->
                    exerciseDao.insertExercise(exercise)
                }
            }
            response
        }
    }
}