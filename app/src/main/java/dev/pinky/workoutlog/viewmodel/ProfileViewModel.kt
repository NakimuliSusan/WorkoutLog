package dev.pinky.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinky.workoutlog.models.CreateProfilerequest
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.ProfileResponse
import dev.pinky.workoutlog.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    val profileRepository = ProfileRepository()
    val  profileResponseLiveData = MutableLiveData<ProfileResponse>()
    val errorLiveData = MutableLiveData<String?>()


    fun createProfile (createProfilerequest: CreateProfilerequest){
        viewModelScope.launch {
            val response = profileRepository.create(createProfilerequest)
            if (response.isSuccessful) {
                profileResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                errorLiveData.postValue(error)
            }
        }
    }

}