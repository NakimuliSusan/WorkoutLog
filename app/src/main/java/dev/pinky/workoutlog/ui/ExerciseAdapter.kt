package dev.pinky.workoutlog.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.pinky.workoutlog.R
import dev.pinky.workoutlog.R.*
import dev.pinky.workoutlog.models.ExerciseCategory
import dev.pinky.workoutlog.models.Exercises

class ExerciseAdapter  (context: Context, var exercises: List<Exercises>):
    ArrayAdapter<Exercises>(context,0,exercises) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)

    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(context).inflate(layout.custom_spinner_exercise_item,parent,false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.tvExerciseSpinner)
        tvSpinnerText.text = exercises.get(position).ExerciseName
        return view
    }
}
