package dev.pinky.workoutlog.repository

import dev.pinky.workoutlog.api.ApiClient
import dev.pinky.workoutlog.api.ApiInterface
import dev.pinky.workoutlog.models.CreateProfilerequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun create (createProfilerequest : CreateProfilerequest)
            = withContext(Dispatchers.IO){
        val response = apiClient.createProfile(createProfilerequest)
        return@withContext response
    }

}