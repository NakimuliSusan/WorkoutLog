package dev.pinky.workoutlog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinky.workoutlog.databinding.RowExerciseNameBinding
import dev.pinky.workoutlog.models.Exercises

class TrackAdapter(val exerciseList: List<Exercises>, val logWorkout: LogWorkout) : RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding =
            RowExerciseNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise = exerciseList.get(position)
        holder.binding.tvExerciseName.text = currentExercise.ExerciseName
        holder.binding.cbdone1.setOnClickListener{
            val weight = holder.binding.etWeightOne.text.toString()
            val reps = holder.binding.etRepOne.text.toString()
            logWorkout.onClickdone(set = 1, weight = weight.toInt(),reps = reps.toInt(),currentExercise.exerciseId)
        }
        holder.binding.cbdone2.setOnClickListener{
            val weight = holder.binding.etWeighttwo.text.toString()
            val reps = holder.binding.etReps2.text.toString()
            logWorkout.onClickdone(set = 2, weight = weight.toInt(),reps = reps.toInt(),currentExercise.exerciseId)
        }
        holder.binding.cbdone3.setOnClickListener{
            val weight = holder.binding.etWeightthree.text.toString()
            val reps = holder.binding.etReps3.text.toString()
            logWorkout.onClickdone(set = 3, weight = weight.toInt(),reps = reps.toInt(),currentExercise.exerciseId)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}

class ExerciseViewHolder(val binding: RowExerciseNameBinding) :
    RecyclerView.ViewHolder(binding.root)

interface  LogWorkout {
    fun onClickdone (set:Int, weight:Int,reps:Int,exerciseId:String)
}
