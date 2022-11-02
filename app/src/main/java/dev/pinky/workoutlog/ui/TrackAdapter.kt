package dev.pinky.workoutlog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.pinky.workoutlog.databinding.RowExerciseNameBinding
import dev.pinky.workoutlog.models.Exercises

class TrackAdapter(val exerciseList: List<Exercises>):RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
    val binding  = RowExerciseNameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise = exerciseList.get(position)
        holder.binding.tvExerciseName.text = currentExercise.ExerciseName
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}

class ExerciseViewHolder(val binding: RowExerciseNameBinding): RecyclerView.ViewHolder(binding.root)
