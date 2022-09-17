package dev.pinky.workoutlog.repository

import dev.pinky.workoutlog.api.ApiClient
import dev.pinky.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiclient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun  fetchExerciseCategories(accessToken: String)
    = withContext(Dispatchers.IO) {
        return@withContext apiclient.fetchExerciseCategories(accessToken)
    }
}