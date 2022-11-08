package dev.pinky.workoutlog.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.databinding.FragmentPlanBinding
import dev.pinky.workoutlog.databinding.FragmentTrackBinding
import dev.pinky.workoutlog.models.WorkoutLogRecord
import dev.pinky.workoutlog.util.Constants
import dev.pinky.workoutlog.viewmodel.ExerciseViewModel
import dev.pinky.workoutlog.viewmodel.WorkoutLogRecordViewModel
import dev.pinky.workoutlog.viewmodel.WorkoutPlanViewModel
import java.time.LocalDate
import java.util.UUID

class TrackFragment : Fragment(), LogWorkout{
    lateinit var binding: FragmentTrackBinding
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels ( )
    val exerciseViewModel : ExerciseViewModel by viewModels ()
    val workoutLogRecordViewModel : WorkoutLogRecordViewModel by viewModels ()
    lateinit var prefs: SharedPreferences
    lateinit var userId:String
    lateinit var workoutplanitemId:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        prefs = requireContext().getSharedPreferences(Constants.prefsFiles,Context.MODE_PRIVATE)
        userId = prefs.getString(Constants.userId,Constants.EMPTY_STRING).toString()
        workoutPlanViewModel.getExistingWorkoutPlans(userId)
        workoutPlanViewModel.workoutPlanLivedata.observe(this, Observer { workoutplan ->
            val workoutPlanId = workoutplan.workoutPlanId
            val dayNumber = LocalDate.now().dayOfWeek.value
            workoutPlanViewModel.getTodayWorkoutPlanItem(workoutPlanId,dayNumber).observe(this, Observer {
                    workoutplanitem ->
                if(workoutplanitem!= null){
                    workoutplanitemId = workoutplanitem.workoutPlanItemId
                    val todayExerciseIds = workoutplanitem.exerciseId
                    exerciseViewModel.getExercisesIds(todayExerciseIds).observe(this, Observer { exercises->
                        val adapter = TrackAdapter(exercises,this)
                        binding.RvTrack.layoutManager = LinearLayoutManager(requireContext())
                        binding.RvTrack.adapter = adapter
                    })

                }
                else{
                    Toast.makeText(requireContext(),"No workoutplan item found for today. create one to continue", Toast.LENGTH_LONG)
                        .show()
                }
            })
        })
    }

    override fun onClickdone(set: Int, weight: Int, reps: Int, exerciseId: String) {
        val workoutLogrecord = WorkoutLogRecord(
            workoutLogId = UUID.randomUUID().toString(),
            date = "",
            exerciseId= exerciseId,
            set = set,
            reps = reps,
            weight = weight,
            workoutPlanItemId = workoutplanitemId,
             userId = userId
        )
        workoutLogRecordViewModel.saveWorkoutLogRecord(workoutLogrecord)
    }
}