package dev.pinky.workoutlog.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.databinding.FragmentPlanBinding
import dev.pinky.workoutlog.databinding.FragmentProfileBinding
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises
import dev.pinky.workoutlog.models.WorkoutPlan
import dev.pinky.workoutlog.util.Constants
import dev.pinky.workoutlog.viewmodel.ExerciseViewModel
import dev.pinky.workoutlog.viewmodel.WorkoutPlanViewModel
import java.util.UUID

class PlanFragment : Fragment() {
    val exerciseViewModel : ExerciseViewModel by viewModels()
    var binding: FragmentPlanBinding? = null
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels ( )

    val bind get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setDaySpinner()
        exerciseViewModel.fetchDbCategories()
        exerciseViewModel.fetchDbExercises()
        setupCategorySpinner()
        bind.btnadditem.setOnClickListener { clickAddItem() }
        checkForExistingWorkoutPlan()
    }

    fun setDaySpinner (){
        val days = listOf("Select Day","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        val days_adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,days)
        days_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spdays.adapter = days_adapter
    }

    fun setupCategorySpinner(){
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories->
            val firstCategory = ExerciseCategory("0","Select Category")
            val displayCategories = mutableListOf(firstCategory)
            displayCategories.addAll(categories)
            val categoryAdapter = ExerciseCategoryAdapter(requireContext(), displayCategories)
            bind.spcategory.adapter = categoryAdapter
            bind.spcategory.onItemSelectedListener = object:OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = displayCategories.get(position)
                    var categoryId = selectedCategory.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                    setupExerciseSpinner()

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        })
    }

    fun setupExerciseSpinner(){
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises->
            val firstCategory = Exercises("","1","","0","Select Exercise")
            val displayExercises = mutableListOf(firstCategory)
            displayExercises.addAll(exercises)
            val exerciseAdapter = ExerciseAdapter(requireContext(), displayExercises)
            bind.spexercise.adapter = exerciseAdapter
        })
    }
    fun clickAddItem () {
        var error = false
        if (bind.spdays.selectedItemPosition == 0){
            error = true
            Toast.makeText(requireContext(),"Select day",Toast.LENGTH_LONG).show()
        }
        if (bind.spcategory.selectedItemPosition == 0){
            error = true
            Toast.makeText(requireContext(),"Select category",Toast.LENGTH_LONG).show()
        }
        if (bind.spexercise.selectedItemPosition == 0){
            error = true
            Toast.makeText(requireContext(),"Select exercise",Toast.LENGTH_LONG).show()
        }
        if(!error){
           val selectedExercise = bind.spexercise.selectedItem as Exercises
            exerciseViewModel.selectedExerciseIds.add(selectedExercise.exerciseId)
            bind.spexercise.setSelection(0)
            bind.spcategory.setSelection(0)

        }

    }

    fun checkForExistingWorkoutPlan(){
        val prefs = requireContext().getSharedPreferences(Constants.prefsFiles,Context.MODE_PRIVATE)
        val userId = prefs.getString(Constants.userId,"").toString()
        workoutPlanViewModel.getExistingWorkoutPlans(userId)
        workoutPlanViewModel.workoutPlanLivedata.observe(this, Observer { workoutPlan ->
            if(workoutPlan == null){
                val newWorkoutPlan = WorkoutPlan(UUID.randomUUID().toString(),userId)
                workoutPlanViewModel.saveWorkoutPlan(newWorkoutPlan)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}