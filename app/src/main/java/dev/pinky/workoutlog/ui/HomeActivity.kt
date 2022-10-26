package dev.pinky.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.databinding.ActivityHomeBinding
import dev.pinky.workoutlog.util.Constants
import dev.pinky.workoutlog.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var token : String
    val exerciseViewModel :  ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(Constants.prefsFiles, MODE_PRIVATE)
        token = sharedPrefs.getString(Constants.accessToken,"").toString()
        exerciseViewModel.fetchDbExercises()
        exerciseViewModel.fetchDbCategories()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExerciseCategories(token)
            }
        } )
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExercises(token)
            }
        } )
        exerciseViewModel.errorLiveData.observe(this, Observer { erroMsg->
            Toast.makeText(this,erroMsg,Toast.LENGTH_LONG).show()
        })
    }

    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, PlanFragment()).commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, TrackFragment())
                        .commit() // changing the fragment from plan to track// completes the transaction allows fragment to be visible
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }

}
//principles of OOP